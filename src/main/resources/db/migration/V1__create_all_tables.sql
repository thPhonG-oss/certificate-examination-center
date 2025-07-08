CREATE TABLE [user] (
    id INT PRIMARY KEY IDENTITY(1,1),
    username VARCHAR(50),
    password VARCHAR(255) NOT NULL,
    email NVARCHAR(100) NOT NULL UNIQUE,
    phone_number NVARCHAR(15),
    full_name NVARCHAR(100),
    gender VARCHAR(1) CHECK ( gender IN ('M', 'F') ),
    dob DATE,
    address NVARCHAR(255),
    enabled BIT NOT NULL DEFAULT 1
);

CREATE TABLE role (
    id INT PRIMARY KEY IDENTITY(1,1),
    name NVARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE user_role (
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES [user](id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE
);

CREATE TABLE khach_hang (
    id_khach_hang INT PRIMARY KEY IDENTITY(1,1),
    ho_ten NVARCHAR(100) NOT NULL,
    ten_co_quan NVARCHAR(100),
    sdt VARCHAR(10) NOT NULL UNIQUE,
    email VARCHAR(255) UNIQUE,
    dia_chi NVARCHAR(255),
    cccd VARCHAR(12) UNIQUE,
    ngay_sinh DATE,
    loai_khach_hang VARCHAR(20) CHECK (loai_khach_hang IN ('INDIVIDUAL', 'ORGANIZATION')) NOT NULL
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
    id_lich_thi INT,
    trang_thai_gia_han VARCHAR(30) CHECK (trang_thai_gia_han IN ('FREE', 'PAID')) DEFAULT NULL,
    lan_gia_han INT DEFAULT 0,
    FOREIGN KEY (id_nhan_vien) REFERENCES [user](id),
    FOREIGN KEY (id_khach_hang) REFERENCES khach_hang(id_khach_hang),
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
    sdt VARCHAR(10) NOT NULL,
    email VARCHAR(255),
    dia_chi NVARCHAR(255),
    cccd VARCHAR(12),
    hinh_anh NVARCHAR(255),
    FOREIGN KEY (id_phieu_dang_ky) REFERENCES phieu_dang_ky(id_phieu_dang_ky)
);

CREATE TABLE phieu_du_thi (
    id_phieu_du_thi INT PRIMARY KEY IDENTITY(1,1),
    id_thi_sinh INT NOT NULL,
    phong_thi VARCHAR(50) NOT NULL,
    FOREIGN KEY (id_thi_sinh) REFERENCES thi_sinh(id_thi_sinh)
);

CREATE TABLE ket_qua_thi (
    id_ket_qua INT PRIMARY KEY IDENTITY(1,1),
    id_phieu_du_thi INT NOT NULL,
    diem VARCHAR(10) NOT NULL,
    trang_thai VARCHAR(20) CHECK (trang_thai IN ('PASSED', 'FAILED')) NOT NULL,
    trang_thai_nhan BIT DEFAULT 0,
    chi_tiet_ket_qua NVARCHAR(255),
    ngay_cap DATE DEFAULT NULL,
    FOREIGN KEY (id_phieu_du_thi) REFERENCES phieu_du_thi(id_phieu_du_thi)
);

CREATE TABLE phieu_gia_han (
    id_gia_han INT PRIMARY KEY IDENTITY(1,1),
    id_phieu_dang_ky INT NOT NULL,
    trang_thai VARCHAR(20) CHECK (trang_thai IN ('PENDING', 'APPROVED', 'REJECTED')) NOT NULL DEFAULT 'PENDING',
    ngay_yeu_cau DATE NOT NULL,
    FOREIGN KEY (id_phieu_dang_ky) REFERENCES phieu_dang_ky(id_phieu_dang_ky)
);

