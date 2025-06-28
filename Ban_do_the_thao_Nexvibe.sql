-- Tạo database
CREATE DATABASE BanQuanAoTT;
GO
USE BanQuanAoTT;
GO

-- Bảng danh mục
CREATE TABLE DanhMuc (
    IdDM INT IDENTITY(1,1) PRIMARY KEY,
    TenDM NVARCHAR(100),
    TrangThai BIT
);
INSERT INTO DanhMuc (TenDM, TrangThai)
VALUES (N'Áo thể thao', 1), (N'Quần thể thao', 1), (N'Phụ kiện', 1);

-- Thương hiệu
CREATE TABLE ThuongHieu (
    IdThuongHieu INT IDENTITY(1,1) PRIMARY KEY,
    MaThuongHieu VARCHAR(50),
    TenThuongHieu NVARCHAR(100),
    TrangThai BIT
);
INSERT INTO ThuongHieu VALUES ('TH001', N'Nike', 1), ('TH002', N'Adidas', 1);

-- Chất liệu
CREATE TABLE ChatLieu (
    IdChatLieu INT IDENTITY(1,1) PRIMARY KEY,
    MaChatLieu VARCHAR(50),
    TenChatLieu NVARCHAR(100),
    TrangThai BIT
);
INSERT INTO ChatLieu VALUES ('CL001', N'Cotton', 1), ('CL002', N'Polyester', 1);

-- Màu sắc
CREATE TABLE MauSac (
    IdMauSac INT IDENTITY(1,1) PRIMARY KEY,
    MaMauSac VARCHAR(50),
    TenMauSac NVARCHAR(100),
    TrangThai BIT
);
INSERT INTO MauSac VALUES ('MS001', N'Đen', 1), ('MS002', N'Trắng', 1);

-- Size
CREATE TABLE Size (
    IdSize INT IDENTITY(1,1) PRIMARY KEY,
    MaSize VARCHAR(50),
    TenSize NVARCHAR(50),
    TrangThai BIT
);
INSERT INTO Size VALUES ('S001', 'M', 1), ('S002', 'L', 1);

-- Sản phẩm
CREATE TABLE SanPham (
    IdSP INT IDENTITY(1,1) PRIMARY KEY,
    MaSP VARCHAR(50),
    TenSP NVARCHAR(100),
    IdDM INT,
    TrangThai BIT,
    FOREIGN KEY (IdDM) REFERENCES DanhMuc(IdDM)
);
INSERT INTO SanPham VALUES ('SP001', N'Áo tập gym nam', 1, 1), ('SP002', N'Quần thể thao nữ', 2, 1);

-- Chi tiết sản phẩm
CREATE TABLE SanPhamCT (
    IdSPCT INT IDENTITY(1,1) PRIMARY KEY,
    MaSPCT VARCHAR(50),
    IdSP INT,
    IdChatLieu INT,
    IdThuongHieu INT,
    IdSize INT,
    IdMauSac INT,
    SoLuong INT,
    Gia DECIMAL(18,2),
    MoTa NVARCHAR(MAX),
    TrangThai BIT,
    FOREIGN KEY (IdSP) REFERENCES SanPham(IdSP),
    FOREIGN KEY (IdChatLieu) REFERENCES ChatLieu(IdChatLieu),
    FOREIGN KEY (IdThuongHieu) REFERENCES ThuongHieu(IdThuongHieu),
    FOREIGN KEY (IdSize) REFERENCES Size(IdSize),
    FOREIGN KEY (IdMauSac) REFERENCES MauSac(IdMauSac)
);
INSERT INTO SanPhamCT 
VALUES 
('CT001', 1, 1, 1, 1, 1, 100, 250000, N'Chất liệu cotton, co giãn tốt', 1),
('CT002', 2, 2, 2, 2, 2, 50, 300000, N'Vải nhẹ, thấm hút mồ hôi', 1);

-- Hình ảnh sản phẩm
CREATE TABLE Img (
    IdImg INT IDENTITY(1,1) PRIMARY KEY,
    IdSPCT INT,
    link NVARCHAR(MAX),
    name NVARCHAR(255),
    size NVARCHAR(50),
    FOREIGN KEY (IdSPCT) REFERENCES SanPhamCT(IdSPCT)
);

-- Tài khoản
CREATE TABLE TaiKhoan (
    IdTK INT IDENTITY(1,1) PRIMARY KEY,
    TaiKhoan NVARCHAR(100),
    MatKhau NVARCHAR(100),
    ChucVu NVARCHAR(50),
    TrangThai BIT
);
INSERT INTO TaiKhoan VALUES ('admin', '123456', 'Admin', 1), ('khach01', '123456', 'KhachHang', 1);

-- Khách hàng
CREATE TABLE KhachHang (
    IdKH INT IDENTITY(1,1) PRIMARY KEY,
    MaKH VARCHAR(50),
    TenKH NVARCHAR(100),
    GioiTinh NVARCHAR(10),
    SDT VARCHAR(20),
    DiaChi NVARCHAR(255),
    IdTK INT,
    TrangThai BIT,
    FOREIGN KEY (IdTK) REFERENCES TaiKhoan(IdTK)
);
INSERT INTO KhachHang VALUES ('KH001', N'Lê Văn A', N'Nam', '0909123456', N'Hà Nội', 2, 1);

-- Nhân viên
CREATE TABLE NhanVien (
    IdNV INT IDENTITY(1,1) PRIMARY KEY,
    MaNV VARCHAR(50),
    TenNV NVARCHAR(100),
    GioiTinh NVARCHAR(10),
    NgaySinh DATE,
    SDT VARCHAR(20),
    Email NVARCHAR(100),
    DiaChi NVARCHAR(255),
    IdTK INT,
    TrangThai BIT,
    FOREIGN KEY (IdTK) REFERENCES TaiKhoan(IdTK)
);
INSERT INTO NhanVien VALUES ('NV001', N'Nguyễn Thị B', N'Nữ', '1995-05-20', '0912345678', 'nv01@gmail.com', N'HCM', 1, 1);

-- Phương thức thanh toán
CREATE TABLE PhuongTT (
    IdPTT INT IDENTITY(1,1) PRIMARY KEY,
    Ten NVARCHAR(50)
);
INSERT INTO PhuongTT VALUES (N'Tiền mặt'), (N'Chuyển khoản'), (N'Momo');

-- Khuyến mãi
CREATE TABLE KhuyenMai (
    IdKM INT IDENTITY(1,1) PRIMARY KEY,
    MaKM VARCHAR(50),
    TenKM NVARCHAR(100),
    NgayBatDau DATETIME,
    NgayKetThuc DATETIME,
    SoLuong INT,
    TrangThai BIT
);
INSERT INTO KhuyenMai VALUES ('KM01', N'Giảm 10%', '2025-06-01', '2025-06-30', 100, 1);

-- Hóa đơn
CREATE TABLE HoaDon (
    IdHD INT IDENTITY(1,1) PRIMARY KEY,
    MaHD VARCHAR(50),
    IdKH INT,
    IdNV INT,
    NgayTao DATETIME,
    NgaySua DATETIME,
    TongTien DECIMAL(18,2),
    TrangThai BIT,
    FOREIGN KEY (IdKH) REFERENCES KhachHang(IdKH),
    FOREIGN KEY (IdNV) REFERENCES NhanVien(IdNV)
);
INSERT INTO HoaDon VALUES ('HD001', 1, 1, GETDATE(), GETDATE(), 500000, 1);

-- Chi tiết hóa đơn
CREATE TABLE HoaDonCT (
    IdHDCT INT IDENTITY(1,1) PRIMARY KEY,
    IdSP INT,
    IdHD INT,
    IdKM INT,
    SoLuong INT,
    DonGia DECIMAL(18,2),
    ThanhTien DECIMAL(18,2),
    TrangThai BIT,
    NgayTao DATETIME,
    NgaySua DATETIME,
    IdPTT INT,
    FOREIGN KEY (IdHD) REFERENCES HoaDon(IdHD),
    FOREIGN KEY (IdSP) REFERENCES SanPhamCT(IdSPCT),
    FOREIGN KEY (IdPTT) REFERENCES PhuongTT(IdPTT),
    FOREIGN KEY (IdKM) REFERENCES KhuyenMai(IdKM)
);
INSERT INTO HoaDonCT VALUES (1, 1, 1, 2, 250000, 500000, 1, GETDATE(), GETDATE(), 1);

-- Giỏ hàng
CREATE TABLE GioHang (
    IdGH INT IDENTITY(1,1) PRIMARY KEY,
    MaGH VARCHAR(50),
    IdKH INT,
    TrangThai BIT,
    NgayTao DATETIME,
    NgaySua DATETIME,
    IdTK INT,
    FOREIGN KEY (IdKH) REFERENCES KhachHang(IdKH),
    FOREIGN KEY (IdTK) REFERENCES TaiKhoan(IdTK)
);
INSERT INTO GioHang VALUES ('GH001', 1, 0, GETDATE(), GETDATE(), 2);

-- Chi tiết giỏ hàng
CREATE TABLE GioHangCT (
    IdGHCT INT IDENTITY(1,1) PRIMARY KEY,
    IdGH INT,
    IdSPCT INT,
    SoLuong INT,
    DonGia DECIMAL(18,2),
    FOREIGN KEY (IdGH) REFERENCES GioHang(IdGH),
    FOREIGN KEY (IdSPCT) REFERENCES SanPhamCT(IdSPCT)
);
INSERT INTO GioHangCT VALUES (1, 1, 1, 250000);

select * from Img
