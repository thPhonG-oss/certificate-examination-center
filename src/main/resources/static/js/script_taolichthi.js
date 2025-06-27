document.addEventListener("DOMContentLoaded", function () {
  // Hiển thị tên nhân viên nếu đã đăng nhập
  // const idNhanVien = localStorage.getItem('id_nhan_vien'); // hoặc sessionStorage

  // if (idNhanVien) {
  //     fetch('http://localhost:8081/nhan_vien')
  //         .then(res => res.json())
  //         .then(nhanVienList => {
  //             const nv = nhanVienList.find(nv => nv.id === idNhanVien);
  //             if (nv) {
  //                 document.getElementById('staffName').textContent = nv.ho_ten;
  //             } else {
  //                 document.getElementById('staffName').textContent = '';
  //             }
  //         });
  // } else {
  //     document.getElementById('staffName').textContent = '';
  // }

  // Xử lý đăng xuất
  var logOutBtn = document.getElementById("logout");
  if (logOutBtn) {
    logOutBtn.addEventListener("click", function () {
      localStorage.removeItem("id_nhan_vien"); // <-- sửa lại key này
      window.location.href = "login.html";
    });
  }

  // Thêm lịch thi mới
  var btnAdd = document.getElementById("btn_them");
  if (btnAdd) {
    btnAdd.addEventListener("click", function (event) {
      event.preventDefault();
      addLichThi();
    });
  }

  // Hiển thị danh sách lịch thi
  start();
});

function formatTimeToHHMMSS(timeStr) {
  if (!timeStr) return "";
  const parts = timeStr.split(":");
  if (parts.length === 2) return timeStr + ":00";
  if (parts.length === 3) return timeStr;
  return timeStr;
}

// Reset form
function resetForm() {
  document.getElementById("ten_chung_chi").value = "";
  document.getElementById("ngay_thi").value = "";
  document.getElementById("gio_thi").value = "";
  document.getElementById("sl_toi_da").value = "";
}

// API endpoint

// Fetch danh sách chứng chỉ
async function fetchChungChi() {
  try {
    const response = await fetch("http://localhost:8081/chung_chi");
    return await response.json();
  } catch (error) {
    console.error("Error fetching chung_chi:", error);
    return [];
  }
}

//------------------------------ CRUD ------------------------------------
const lichThiAPI = "http://localhost:8081/lich_thi";
// Lấy danh sách lịch thi
function getLichThi(callback) {
  fetch(lichThiAPI, {
    method: "GET",
    headers: { "Content-Type": "application/json" },
  })
    .then((response) => {
      if (!response.ok) throw new Error("Network response was not ok");
      return response.json();
    })
    .then(callback)
    .catch((error) => console.error("Error fetching lich_thi:", error));
}

// Thêm lịch thi mới
function createLichThi(data, callback) {
  fetch(lichThiAPI, {
    method: "POST",
    body: JSON.stringify(data),
    headers: { "Content-Type": "application/json" },
  })
    .then((response) => {
      if (!response.ok) throw new Error("Network response was not ok");
      return response.json();
    })
    .then(callback)
    .catch((error) => console.error("Error creating lich_thi:", error));
}

// Xóa lịch thi
function deleteLichThi(id, callback) {
  fetch(`${lichThiAPI}/${id}`, {
    method: "DELETE",
    headers: { "Content-Type": "application/json" },
  })
    .then((response) => {
      if (!response.ok) throw new Error("Network response was not ok");
      return response.json();
    })
    .then(() => {
      var lichThiItem = document.querySelector(".lichthi-item-" + id);
      if (lichThiItem) {
        lichThiItem.remove();
      }
      if (typeof callback === "function") callback();
    })
    .catch((error) => console.error("Error deleting lich_thi:", error));
}

// Cập nhật lịch thi
function updateLichThi(id, data, callback) {
  fetch(`${lichThiAPI}/${id}`, {
    method: "PUT",
    body: JSON.stringify(data),
    headers: { "Content-Type": "application/json" },
  })
    .then((response) => {
      if (!response.ok) throw new Error("Network response was not ok");
      return response.json();
    })
    .then(callback)
    .catch((error) => console.error("Error updating lich_thi:", error));
}

//---------------------------------------- HÀM ---------------------------------------

// Render danh sách lịch thi
async function renderLichThi(list_lichthi) {
  var tbody = document.querySelector(".table-responsive tbody");
  if (!tbody) {
    console.error("No tbody element found");
    return;
  }
  try {
    // Lấy danh sách chứng chỉ
    const chungChiList = await fetchChungChi();

    var htmls = list_lichthi
      .map(function (lichthi, index) {
        // Tìm tên chứng chỉ theo certification_id
        const chungChi = chungChiList.find(
          (c) => String(c.id) === String(lichthi.certification_id)
        );
        return `
                <tr class="lichthi-item-${lichthi.id}">
                    <td>${index + 1}</td>
                    <td>${chungChi ? chungChi.name : ""}</td>
                    <td>${lichthi.date || ""}</td>
                    <td>${lichthi.time ? lichthi.time.slice(0, 5) : ""}</td>
                    <td>${lichthi.maxParticipants || ""}</td>
                    <td>${lichthi.currentParticipants || 0}</td>
                    <td class="edit-icons">
                        <button type="button" class="btn btn-sm btn-warning" onclick="editLichThi('${
                          lichthi.id
                        }')"><i class="bi bi-pencil"></i></button>
                        <button type="button" class="btn btn-sm btn-danger" onclick="deleteLichThi('${
                          lichthi.id
                        }', getLichThi.bind(null, renderLichThi))"><i class="bi bi-trash"></i></button>
                    </td>
                </tr>
            `;
      })
      .join("");

    tbody.innerHTML = htmls;
  } catch (error) {
    console.error("Error rendering lich_thi:", error);
    tbody.innerHTML = '<tr><td colspan="7">Error loading data</td></tr>';
  }
}

async function addLichThi() {
  var tenChungChiInput = document.getElementById("ten_chung_chi");
  var ngayThiInput = document.getElementById("ngay_thi");
  var gioThiInput = document.getElementById("gio_thi");
  var slToiDaInput = document.getElementById("sl_toi_da");

  if (tenChungChiInput && ngayThiInput && gioThiInput && slToiDaInput) {
    var ten_chung_chi = tenChungChiInput.value.trim();
    var date = ngayThiInput.value.trim();
    var time = gioThiInput.value.trim();
    var maxParticipants = slToiDaInput.value.trim();

    if (
      ten_chung_chi === "" ||
      date === "" ||
      time === "" ||
      maxParticipants === ""
    ) {
      alert("Vui lòng điền đầy đủ thông tin vào tất cả các ô.");
      return;
    }
    // Lấy certification_id từ tên chứng chỉ
    const chungChiList = await fetchChungChi();
    const chungChi = chungChiList.find((c) => c.name === ten_chung_chi);
    if (!chungChi) {
      alert("Không tìm thấy chứng chỉ này!");
      return;
    }
    var certification_id = chungChi.id;

    // Kiểm tra trùng giờ, trùng lịch thi cho cùng chứng chỉ và cách nhau ít nhất 2 tiếng
    const res = await fetch("http://localhost:8081/lich_thi");
    const lichThiList = await res.json();

    const trungLich = lichThiList.filter(
      (lt) => lt.certification_id === certification_id && lt.date === date
    );

    const [hMoi, mMoi] = time.split(":").map(Number);
    const timeMoi = hMoi * 60 + mMoi;

    for (const lt of trungLich) {
      const [hCu, mCu] = lt.time.split(":").map(Number);
      const timeCu = hCu * 60 + mCu;
      const diff = Math.abs(timeMoi - timeCu);

      if (diff === 0) {
        alert("Đã có lịch thi trùng giờ cho chứng chỉ này!");
        return;
      }
      if (diff < 120) {
        // 2 tiếng = 120 phút
        alert("Lịch thi của chứng chỉ này phải cách nhau ít nhất 2 tiếng!");
        return;
      }
    }

    var data = {
      certification_id: certification_id,
      date: date,
      time: time.length === 5 ? time + ":00" : time, // chuẩn hóa về HH:mm:ss
      maxParticipants: Number(maxParticipants),
      currentParticipants: 0, // Mặc định khi tạo mới
    };

    createLichThi(data, function () {
      getLichThi(renderLichThi);
      resetForm();
    });
  } else {
    alert("Không tìm thấy đủ các trường nhập liệu!");
  }
}

// Sửa lịch thi
function editLichThi(id) {
  fetch(`${lichThiAPI}/${id}`)
    .then(function (response) {
      if (!response.ok) throw new Error("Network response was not ok");
      return response.json();
    })
    .then(function (lichThi) {
      if (lichThi) {
        document.getElementById("modalOverlay").style.display = "flex";

        fetchChungChi().then(function (chungChiList) {
          // Tìm tên chứng chỉ theo certification_id
          const chungChi = chungChiList.find(
            (c) => String(c.id) === String(lichThi.certification_id)
          );
          document.getElementById("modal_tenChungChi").value = chungChi
            ? chungChi.name
            : "";
          document.getElementById("modal_ngayThi").value = lichThi.date;
          document.getElementById("modal_gioThi").value = lichThi.time
            ? lichThi.time.slice(0, 5)
            : "";
          document.getElementById("modal_soLuongToiDa").value =
            lichThi.maxParticipants;

          var btnSave = document.getElementById("modalSaveBtn");
          btnSave.onclick = null;
          btnSave.onclick = async function () {
            var ten_chung_chi = document
              .getElementById("modal_tenChungChi")
              .value.trim();
            var date = document.getElementById("modal_ngayThi").value.trim();
            var time = document.getElementById("modal_gioThi").value.trim();
            var maxParticipants = document
              .getElementById("modal_soLuongToiDa")
              .value.trim();

            if (!ten_chung_chi || !date || !time || !maxParticipants) {
              alert("Vui lòng điền đầy đủ thông tin");
              return;
            }

            // Lấy certification_id từ tên chứng chỉ
            const chungChiList2 = await fetchChungChi();
            const chungChi2 = chungChiList2.find(
              (c) => c.name === ten_chung_chi
            );
            if (!chungChi2) {
              alert("Không tìm thấy chứng chỉ này!");
              return;
            }
            var certification_id = chungChi2.id;

            // Kiểm tra trùng giờ, trùng lịch thi cho cùng chứng chỉ và cách nhau ít nhất 2 tiếng
            const res = await fetch("http://localhost:8081/lich_thi");
            const lichThiList = await res.json();

            // Lọc các lịch thi cùng chứng chỉ, cùng ngày, khác id hiện tại
            const trungLich = lichThiList.filter(
              (lt) =>
                lt.certification_id === certification_id &&
                lt.date === date &&
                String(lt.id) !== String(id)
            );

            function parseTimeToMinutes(str) {
              if (!str) return null;
              const parts = str.split(":").map(Number);
              if (parts.length < 2 || isNaN(parts[0]) || isNaN(parts[1]))
                return null;
              return parts[0] * 60 + parts[1];
            }

            const timeMoi = parseTimeToMinutes(time);

            for (const lt of trungLich) {
              const timeCu = parseTimeToMinutes(lt.time);
              if (timeMoi === null || timeCu === null) continue; // Bỏ qua nếu lỗi

              const diff = Math.abs(timeMoi - timeCu);

              if (diff === 0) {
                alert("Đã có lịch thi trùng giờ cho chứng chỉ này!");
                return;
              }
              if (diff < 120) {
                // 2 tiếng = 120 phút
                alert(
                  "Lịch thi của chứng chỉ này phải cách nhau ít nhất 2 tiếng!"
                );
                return;
              }
            }

            var formData = {
              certification_id: certification_id,
              date: date,
              time: time.length === 5 ? time + ":00" : time,
              maxParticipants: Number(maxParticipants),
              currentParticipants: lichThi.currentParticipants || 0,
            };

            updateLichThi(id, formData, function () {
              getLichThi(renderLichThi);
              document.getElementById("modalOverlay").style.display = "none";
            });
          };

          var btnCancel = document.getElementById("modalCancelBtn");
          btnCancel.onclick = function () {
            document.getElementById("modalOverlay").style.display = "none";
          };
        });
      }
    })
    .catch(function (error) {
      console.error("Error editing lich_thi:", error);
    });
}

// Tìm kiếm lịch thi
document.addEventListener("DOMContentLoaded", function () {
  const searchButton = document.getElementById("searchButton");
  const searchInput = document.getElementById("searchInput");
  const searchDate = document.getElementById("searchDate");

  searchButton.addEventListener("click", function () {
    const searchTerm = searchInput.value.trim().toLowerCase();
    const searchDay = searchDate.value;
    if (searchTerm || searchDay) {
      searchLichThi(searchTerm, searchDay);
    } else {
      getLichThi(renderLichThi);
    }
  });

  searchInput.addEventListener("input", function () {
    if (!searchInput.value.trim() && !searchDate.value) {
      getLichThi(renderLichThi);
    }
  });

  searchDate.addEventListener("change", function () {
    if (!searchInput.value.trim() && !searchDate.value) {
      getLichThi(renderLichThi);
    }
  });
});

function searchLichThi(searchTerm, searchDay) {
  fetch(lichThiAPI)
    .then(function (response) {
      return response.json();
    })
    .then(async function (listLichThi) {
      const chungChiList = await fetchChungChi();

      const filtered = listLichThi.filter(function (lichthi) {
        if (searchDay && lichthi.date !== searchDay) {
          return false;
        }
        if (searchTerm) {
          const chungChi = chungChiList.find(
            (c) => String(c.id) === String(lichthi.certification_id)
          );
          if (!chungChi || !chungChi.name.toLowerCase().includes(searchTerm)) {
            return false;
          }
        }
        return true;
      });

      renderLichThi(filtered);
    })
    .catch((error) => console.error("Error searching lich_thi:", error));
}

function start() {
  getLichThi(renderLichThi);
}
