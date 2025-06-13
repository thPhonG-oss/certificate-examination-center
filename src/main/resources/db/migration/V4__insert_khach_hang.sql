-- 4. Insert khach_hang (Customers)
INSERT INTO khach_hang (ho_ten, ten_co_quan, sdt, email, dia_chi, cccd, ngay_sinh, loai_khach_hang)
VALUES (N'Nguyễn Văn A', NULL, '0912345678', 'nguyenvana@email.com', N'123 Lê Lợi, Q.1, TP.HCM', '079202000001',
        '2000-05-15', 'INDIVIDUAL'),
       (N'Trần Thị B', NULL, '0912345679', 'tranthib@email.com', N'456 Hai Bà Trưng, Q.3, TP.HCM', '079202000002',
        '1995-08-20', 'INDIVIDUAL'),
       (N'Đại diện XYZ', N'Trung Tâm Anh Ngữ XYZ', '0912345680', 'contact@xyz.edu.vn', N'789 Nguyễn Huệ, Q.1, TP.HCM',
        NULL, NULL, 'ORGANIZATION');