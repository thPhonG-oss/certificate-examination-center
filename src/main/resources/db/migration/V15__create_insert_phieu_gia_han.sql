
CREATE TABLE phieu_gia_han (
    id_gia_han INT PRIMARY KEY IDENTITY(1,1),
    id_phieu_dang_ky INT NOT NULL,
    trang_thai VARCHAR(20) CHECK (trang_thai IN ('PENDING', 'APPROVED', 'REJECTED')) NOT NULL DEFAULT 'PENDING',
    ngay_yeu_cau DATE NOT NULL,
    le_phi_gia_han DECIMAL(18, 2) NOT NULL,
    FOREIGN KEY (id_phieu_dang_ky) REFERENCES phieu_dang_ky(id_phieu_dang_ky)
);

INSERT INTO phieu_gia_han (id_phieu_dang_ky, trang_thai, ngay_yeu_cau, le_phi_gia_han)
VALUES 
(1, 'PENDING', '2025-06-01', 30000),
(2, 'APPROVED', '2025-06-05', 0),
(3, 'REJECTED', '2025-06-10', 30000);
