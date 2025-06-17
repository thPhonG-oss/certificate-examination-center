CREATE TABLE khach_hang (
    id_khach_hang INT PRIMARY KEY IDENTITY(1,1),
    ho_ten NVARCHAR(100) NOT NULL,
    ten_co_quan NVARCHAR(100),
    sdt VARCHAR(10) NOT NULL UNIQUE,
    email VARCHAR(255) UNIQUE,
    dia_chi VARCHAR(255),
    cccd VARCHAR(12) UNIQUE,
    ngay_sinh DATE,
    loai_khach_hang VARCHAR(20) CHECK (loai_khach_hang IN ('INDIVIDUAL', 'ORGANIZATION')) NOT NULL
);

CREATE TABLE nhan_vien (
    id_nhan_vien INT PRIMARY KEY IDENTITY(1,1),
    ho_ten NVARCHAR(100) NOT NULL,
    gioi_tinh VARCHAR(1) CHECK (gioi_tinh IN ('M', 'F')) NOT NULL,
    email VARCHAR(255) UNIQUE,
    sdt VARCHAR(10) NOT NULL UNIQUE,
    dia_chi NVARCHAR(255),
    ngay_sinh DATE
);

CREATE TABLE vai_tro (
    id_vai_tro INT PRIMARY KEY IDENTITY(1,1),
    ten_vai_tro VARCHAR(20) NOT NULL UNIQUE
)

CREATE TABLE tai_khoan (
    id_tai_khoan INT PRIMARY KEY IDENTITY(1,1),
    ten_dang_nhap VARCHAR(50) NOT NULL UNIQUE,
    mat_khau VARCHAR(255) NOT NULL,
    id_nhan_vien INT,
    id_vai_tro INT,
    trang_thai VARCHAR(20) CHECK (trang_thai IN ('ACTIVE', 'INACTIVE')) NOT NULL DEFAULT 'ACTIVE',
    FOREIGN KEY (id_nhan_vien) REFERENCES nhan_vien(id_nhan_vien),
    FOREIGN KEY (id_vai_tro) REFERENCES vai_tro(id_vai_tro)
);

CREATE TABLE chung_chi (
    id_chung_chi INT PRIMARY KEY IDENTITY(1,1),
    ten_chung_chi NVARCHAR(100) NOT NULL,
    gia DECIMAL(18, 2) NOT NULL
);

CREATE TABLE lich_thi (
    id_lich_thi INT PRIMARY KEY IDENTITY(1,1),
    ngay_thi DATE NOT NULL,
    gio_thi TIME NOT NULL,
    id_chung_chi INT NOT NULL,
    so_luong_dk INT DEFAULT 0,
    sl_toi_da INT NOT NULL,
    FOREIGN KEY (id_chung_chi) REFERENCES chung_chi(id_chung_chi)
);

CREATE TABLE phieu_dang_ky (
    id_phieu_dang_ky INT PRIMARY KEY IDENTITY(1,1),
    id_khach_hang INT NOT NULL,
    ngay_dang_ky DATE NOT NULL,
    trang_thai VARCHAR(20) CHECK (trang_thai IN ('PENDING', 'CONFIRMED', 'CANCELED')) NOT NULL DEFAULT 'PENDING',
    id_nhan_vien INT,
    id_gia_han INT,
    trang_thai_gia_han VARCHAR(30) CHECK (trang_thai_gia_han IN ('FREE', 'PAID')) DEFAULT NULL,
    FOREIGN KEY (id_nhan_vien) REFERENCES nhan_vien(id_nhan_vien),
    FOREIGN KEY (id_khach_hang) REFERENCES khach_hang(id_khach_hang)
);

CREATE TABLE chi_tiet_phieu_dang_ky (
    id_phieu_dang_ky INT NOT NULL,
    id_lich_thi INT NOT NULL,
    PRIMARY KEY (id_phieu_dang_ky, id_lich_thi),
    FOREIGN KEY (id_phieu_dang_ky) REFERENCES phieu_dang_ky(id_phieu_dang_ky),
    FOREIGN KEY (id_lich_thi) REFERENCES lich_thi(id_lich_thi)
);

CREATE TABLE hoa_don (
    id_hoa_don INT PRIMARY KEY IDENTITY(1,1),
    id_phieu_dang_ky INT NOT NULL,
    ngay_tao DATE NOT NULL,
    giam_gia DECIMAL(18, 2) DEFAULT 0,
    tong_tien DECIMAL(18, 2) NOT NULL,
    trang_thai VARCHAR(20) CHECK (trang_thai IN ('PENDING', 'PAID', 'CANCELED')) NOT NULL DEFAULT 'PENDING',
    phuong_thuc_tt VARCHAR(20) CHECK (phuong_thuc_tt IN ('CASH', 'ONLINE')) NOT NULL DEFAULT 'CASH',
    FOREIGN KEY (id_phieu_dang_ky) REFERENCES phieu_dang_ky(id_phieu_dang_ky)
);

CREATE TABLE thi_sinh (
    id_thi_sinh INT PRIMARY KEY IDENTITY(1,1),
    id_phieu_dang_ky INT NOT NULL,
    ho_ten NVARCHAR(100) NOT NULL,
    gioi_tinh VARCHAR(1) CHECK (gioi_tinh IN ('M', 'F')) NOT NULL,
    ngay_sinh DATE NOT NULL,
    sdt VARCHAR(10) NOT NULL UNIQUE,
    email VARCHAR(255) UNIQUE,
    dia_chi NVARCHAR(255),
    cccd VARCHAR(12) UNIQUE,
    FOREIGN KEY (id_phieu_dang_ky) REFERENCES phieu_dang_ky(id_phieu_dang_ky)
);

CREATE TABLE phieu_du_thi (
    id_phieu_du_thi INT PRIMARY KEY IDENTITY(1,1),
    id_thi_sinh INT NOT NULL,
    phong_thi VARCHAR(50) NOT NULL,
    FOREIGN KEY (id_thi_sinh) REFERENCES thi_sinh(id_thi_sinh)
);
-- pass: có chứng chỉ, failed: không có chứng chỉ
CREATE TABLE ket_qua_thi (
    id_ket_qua INT PRIMARY KEY IDENTITY(1,1),
    id_phieu_du_thi INT NOT NULL,
    diem VARCHAR(10) NOT NULL,
    trang_thai VARCHAR(20) CHECK (trang_thai IN ('PASSED', 'FAILED')) NOT NULL,
    FOREIGN KEY (id_phieu_du_thi) REFERENCES phieu_du_thi(id_phieu_du_thi)
);



