document.addEventListener('DOMContentLoaded', function () {

    // Hiển thị tên nhân viên nếu đã đăng nhập
    const idNhanVien = localStorage.getItem('id_nhan_vien'); // hoặc sessionStorage

    if (idNhanVien) {
        fetch('http://localhost:3000/nhan_vien')
            .then(res => res.json())
            .then(nhanVienList => {
                const nv = nhanVienList.find(nv => nv.id === idNhanVien);
                if (nv) {
                    document.getElementById('staffName').textContent = nv.ho_ten;
                } else {
                    document.getElementById('staffName').textContent = '';
                }
            });
    } else {
        document.getElementById('staffName').textContent = '';
    }

    // Xử lý đăng xuất
    var logOutBtn = document.getElementById('logout');
    if (logOutBtn) {
        logOutBtn.addEventListener('click', function () {
            localStorage.removeItem('id_nhan_vien'); // <-- sửa lại key này
            window.location.href = "login.html";
        });
    }


    // Nuts Thêm hóa đơn mới
    var btnAdd = document.getElementById('btn_them');
    if (btnAdd) {
        btnAdd.addEventListener('click', function(event) {
            event.preventDefault();
            addHoaDon();
        });
    }
        // Thêm sự kiện tắt modal khi bấm nút Đóng
    var btnCloseModal = document.getElementById('btnCloseModal');
    if (btnCloseModal) {
        btnCloseModal.addEventListener('click', function() {
            $('#modalChiTietHoaDon').modal('hide');
        });
    }

    // Hiển thị danh sách hóa đơn
    start();
});


// Xử lý khi bấm nút tìm kiếm phiếu
document.getElementById('btn_tim_kiem_phieu').addEventListener('click', async function() {
    const idPdk = document.getElementById('id_phieu_dang_ky').value.trim();
    if (!idPdk) {
        alert('Vui lòng nhập mã phiếu đăng ký');
        return;
    }
    try {
        const res = await fetch(`http://localhost:8081/invoice_information/${idPdk}`);
        if (res.ok) {
            const info = await res.json();
            document.getElementById('ngay_tao').value = new Date().toISOString().slice(0, 10);
            document.getElementById('tong_tien').value = info.sumMoney || 0;
            document.getElementById('giam_gia').value = info.discount || 0;
        } else {
            alert('Không tìm thấy thông tin phiếu!');
            document.getElementById('ngay_tao').value = '';
            document.getElementById('tong_tien').value = '';
            document.getElementById('giam_gia').value = '';
        }
    } catch (error) {
        alert('Lỗi khi tìm kiếm phiếu!');
        document.getElementById('ngay_tao').value = '';
        document.getElementById('tong_tien').value = '';
        document.getElementById('giam_gia').value = '';
    }
});



// Hàm reset form
function resetForm() {
    document.getElementById('id_phieu_dang_ky').value = '';
    document.getElementById('ngay_tao').value = '';
    document.getElementById('tong_tien').value = '';
    document.getElementById('giam_gia').value = '';
    var radios = document.getElementsByName('phuong_thuc_tt');
    radios.forEach(r => r.checked = false);
}

const hoaDonAPI = "http://localhost:8081/invoice";


//------------------ CRUD -----------------------
// Lấy danh sách hóa đơn
function getHoaDon(callback) {
    fetch("http://localhost:8081/api/invoice", {
        method: 'GET',
        headers: { "Content-Type": "application/json" }
    })
    .then(response => {
        if (!response.ok) throw new Error('Network response was not ok');
        return response.json();
    })
    .then(callback)
    .catch(error => console.error('Error fetching hoa_don:', error));
}

// Tạo
function createHoaDon(data, callback) {
    fetch("http://localhost:8081/invoice/new_invoice", {
        method: 'POST',
        body: JSON.stringify(data),
        headers: { "Content-Type": "application/json" }
    })
    .then(response => {
        if (!response.ok) throw new Error('Network response was not ok');
        return response.json();
    })
    .then(callback)
    .catch(error => console.error('Error creating hoa_don:', error));
}


// Cập nhật hóa đơn
function updateHoaDon(idInvoice, data, callback) {
    fetch(`http://localhost:8081/invoice/update/${idInvoice}`, {
        method: 'POST',
        body: JSON.stringify(data),
        headers: { "Content-Type": "application/json" }
    })
    .then(response => {
        if (!response.ok) throw new Error('Network response was not ok');
        return response.json();
    })
    .then(callback)
    .catch(error => console.error('Error updating hoa_don:', error));
}

// ------------------------- HÀM -----------------------

async function renderHoaDon(list_hoadon) {
    var tbody = document.querySelector('.table-responsive tbody');
    if (!tbody) {
        console.error('No tbody element found');
        return;
    }

    try {
        var htmls = list_hoadon.map(function(hoadon, index) {
            // Tính số tiền thanh toán
            const tongTien = Number(hoadon.total_amount) || 0;
            const giamGia = Number(hoadon.sales_amount) || 0;
            const thanhToan = tongTien - giamGia;

            return `
                <tr class="hoadon-item-${hoadon.idInvoice}">
                    <td>${index + 1}</td>
                    <td>${hoadon.id || ''}</td>
                    <td>${hoadon.registration_form_id || ''}</td>
                    <td>${hoadon.invoice_date || ''}</td>
                    <td>${hoadon.status || ''}</td>
                    <td>${tongTien.toLocaleString()}</td>
                    <td>${giamGia.toLocaleString()}</td>
                    <td>${thanhToan.toLocaleString()}</td>
                    <td>${hoadon.payment_method || ''}</td>
                    <td>
                        <button type="button" class="btn btn-warning btn-sm" onclick="editHoaDon('${hoadon.id}')">
                            <i class="bi bi-pencil"></i>
                        </button>
                    </td>
                </tr>
            `;
        }).join('');

        tbody.innerHTML = htmls;
    } catch (error) {
        console.error('Error rendering hoa_don:', error);
        tbody.innerHTML = '<tr><td colspan="10">Error loading data</td></tr>';
    }
}

async function addHoaDon() {
    var phieuDangKyInput = document.getElementById('id_phieu_dang_ky');
    var tongTienInput = document.getElementById('tong_tien');
    var giamGiaInput = document.getElementById('giam_gia');
    var phuongThucInput = document.querySelector('input[name="phuong_thuc_tt"]:checked');

    if (phieuDangKyInput && tongTienInput && phuongThucInput && giamGiaInput) {
        var id_phieu_dang_ky = phieuDangKyInput.value.trim();
        var tong_tien = tongTienInput.value.trim();
        var phuong_thuc_tt = phuongThucInput.value;
        var giam_gia = giamGiaInput.value.trim();

        if (id_phieu_dang_ky === "" || tong_tien === "" || phuong_thuc_tt === "" || giam_gia  === "") {
            alert("Vui lòng điền đầy đủ thông tin vào tất cả các ô.");
            return;
        }

        // Kiểm tra phiếu đăng ký đã lập hóa đơn chưa
        const resInvoice = await fetch('http://localhost:8081/api/invoice');
        const invoiceList = await resInvoice.json();
        const daCoHoaDon = invoiceList.some(invoice => invoice.registration_form_id === id_phieu_dang_ky);
        if (daCoHoaDon) {
            alert("Phiếu đăng ký này đã được lập hóa đơn, không thể lập thêm!");
            resetForm();
            return;
        }

        var data = {
            registration_form_id: id_phieu_dang_ky,
            invoice_date: new Date().toISOString().slice(0, 10),
            sales_amount: giam_gia,
            total_amount: String(tong_tien),
            status: "PAID",
            payment_method: phuong_thuc_tt
        };
        createHoaDon( data, function() {
            getHoaDon(renderHoaDon);
            resetForm();
        });
    } else {
        alert("Không tìm thấy đủ các trường nhập liệu!");
    }
}


async function editHoaDon(idInvoice) {
    fetch(`http://localhost:8081/invoice/${idInvoice}`)
        .then(async function(response) {
            if (!response.ok) throw new Error('Network response was not ok');
            return response.json();
        })
        .then(function(hoaDon) {
            if (hoaDon) {
                document.getElementById('modalOverlayHoaDon').style.display = 'flex';

                // Điền dữ liệu vào các input, dùng ?? để giữ giá trị 0
                document.getElementById('modal_id_phieu_dang_ky').value = hoaDon.registration_form_id ?? '';
                document.getElementById('modal_ngay_tao').value = hoaDon.invoice_date ?? '';
                document.getElementById('modal_tong_tien').value = hoaDon.total_amount ?? '';
                document.getElementById('modal_giam_gia').value = hoaDon.sales_amount ?? '';

                // Set radio button theo phương thức thanh toán
                if (hoaDon.payment_method === 'Tiền mặt') {
                    document.getElementById('modal_CASH').checked = true;
                    document.getElementById('modal_ONLINE').checked = false;
                } else if (hoaDon.payment_method === 'Chuyển khoản') {
                    document.getElementById('modal_ONLINE').checked = true;
                    document.getElementById('modal_CASH').checked = false;
                } else {
                    document.getElementById('modal_CASH').checked = false;
                    document.getElementById('modal_ONLINE').checked = false;
                }

                // Sự kiện nút lưu
                var btnSave = document.getElementById('modalSaveBtnHoaDon');
                btnSave.onclick = null;
                btnSave.onclick = function() {
                    // Cập nhật lại dữ liệu từ form
                    let regFormId = document.getElementById('modal_id_phieu_dang_ky').value.trim();
                    if (!regFormId) regFormId = hoaDon.registration_form_id;

                    hoaDon.registration_form_id = regFormId;
                    hoaDon.invoice_date = document.getElementById('modal_ngay_tao').value.trim();
                    hoaDon.total_amount = document.getElementById('modal_tong_tien').value.trim();
                    hoaDon.sales_amount = document.getElementById('modal_giam_gia').value.trim();
                    hoaDon.payment_method = document.querySelector('input[name="modal_phuong_thuc_tt"]:checked')?.value ?? '';

                    // Kiểm tra dữ liệu trước khi gửi
                    if (!hoaDon.payment_method) {
                        alert("Vui lòng chọn phương thức thanh toán");
                        return;
                    }

                    updateHoaDon(idInvoice, hoaDon, function() {
                        getHoaDon(renderHoaDon);
                        document.getElementById('modalOverlayHoaDon').style.display = 'none';
                    });
                };

                // Sự kiện nút hủy
                var btnCancel = document.getElementById('modalCancelBtnHoaDon');
                btnCancel.onclick = function() {
                    document.getElementById('modalOverlayHoaDon').style.display = 'none';
                };
            }
        })
        .catch(function(error) {
            console.error('Error editing hoa_don:', error);
        });
}


function searchHoaDon(searchTerm) {
    fetch('http://localhost:8081/api/invoice')
        .then(res => res.json())
        .then(function(listHoaDon) {
            const filtered = listHoaDon.filter(function(hoaDon) {
                return String(hoaDon.registration_form_id)  // Ép kiểu ở đây
                    .toLowerCase()
                    .includes(searchTerm.trim().toLowerCase());
            });
            renderHoaDon(filtered);
        });
}


// Gắn sự kiện cho nút tìm kiếm
document.addEventListener('DOMContentLoaded', function() {
    const searchButton = document.getElementById('searchButton');
    const searchInput = document.getElementById('searchInput');

    searchButton.addEventListener('click', function() {
        const searchTerm = searchInput.value.trim();
        if (searchTerm) {
            searchHoaDon(searchTerm);
        } else {
            getHoaDon(renderHoaDon);
        }
    });

    searchInput.addEventListener('input', function() {
        if (!searchInput.value.trim()) {
            getHoaDon(renderHoaDon);
        }
    });
});
function start (){
    getHoaDon(renderHoaDon);
}
