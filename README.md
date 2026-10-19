# Final Exam - Product Management (JSP / Servlet / JDBC)

Ứng dụng quản lý sản phẩm: xem danh sách, thêm, sửa, xóa có xác nhận; lưu dữ liệu trong MySQL.

## Yêu cầu
- JDK 17+
- Maven 3.8+
- MySQL 8+
- Apache Tomcat 10.1+

## Bước 1: Tạo database
Dùng MySQL Workbench, phpMyAdmin hoặc dòng lệnh chạy file `database.sql`:

```bash
mysql -u root -p < database.sql
```

## Bước 2: Cấu hình kết nối
Ứng dụng đọc biến môi trường (environment variables):
- `DB_URL` (mặc định `jdbc:mysql://localhost:3306/product_management?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Ho_Chi_Minh`)
- `DB_USER` (mặc định `root`)
- `DB_PASSWORD` (mặc định chuỗi rỗng)

Nên cấu hình `DB_PASSWORD` theo tài khoản MySQL của máy trước khi chạy Tomcat. Không đưa mật khẩu thật vào Git.

## Bước 3: Đóng gói và chạy
```bash
mvn clean package
```
Chép `target/product-management.war` vào thư mục `webapps` của Tomcat 10.1+, khởi động Tomcat và truy cập:
http://localhost:8080/product-management/

## Kiến trúc
- `model/Product.java`: dữ liệu sản phẩm.
- `dao/ProductDAO.java`: JDBC, PreparedStatement, try-with-resources.
- `web/ProductServlet.java`: controller xử lý GET/POST.
- `WEB-INF/views/list.jsp`: bảng sản phẩm.
- `WEB-INF/views/form.jsp`: biểu mẫu thêm/sửa.
- `database.sql`: bảng và dữ liệu mẫu.

Xóa sử dụng POST và hộp thoại xác nhận JavaScript. Đây là bài thực hành; triển khai thực tế nên bổ sung đăng nhập, phân quyền và bảo vệ CSRF.

Mã nguồn được viết riêng cho bài thực hành này, không sao chép từ dự án bên ngoài.
