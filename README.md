<p id="top" align="center">
  <img src="https://fit.hcmute.edu.vn/Resources/Images/SubDomain/fit/logo-news.png" alt="Example Image">
</p>

<h1 id="top" align="center">Group 14 Mobile programming</h1>
<h2 align="right">GVHD : Nguyễn Hữu Trung</h2>


<style>
a[href="#top"] {
    display: inline-block;
    padding: 10px;
    background-color: #000;
    color: #fff;
    text-decoration: none;
    position: fixed;
    bottom: 20px;
    right: 20px;
    border-radius: 5px;
}

a[href="#top"]:hover {
    background-color: #333;
}
</style>

<!-- Nội dung README.md tiếp theo -->

# Member

| Họ và tên               | Mssv     | Description          |
| :--------               | :------- | :--------------------|
| Ngô Diệp Quang Huy      | 20110650 |                      |
| Nguyễn Hoàng Toàn       | 20110739 |                      |

<h1 align="center">Xây dựng phần mềm bán đồ nội thất coza express</h1>

### Prerequisites

Trước khi cài đặt và chỉnh sửa web app, hãy chắc rằng trên máy đã cài đặt:

* Công cụ hỗ trợ lập trình: Android Studio (khuyên dùng), hoặc bất kì công cụ khác có tích hợp môi trường phát triển Java (IDE) khác.
* Phần mềm web server: Spring Tool Suite 
* Hệ quản trị cơ sở dữ liệu: MongoDB NoSQL (khuyên dùng).

# Mô tả dự án:

### 1. Chức năng chính:

- Đăng nhập và đăng ký: Người dùng có thể tạo tài khoản mới hoặc đăng nhập vào ứng dụng.
- Hiển thị danh sách sản phẩm: Ứng dụng hiển thị danh sách các sản phẩm nội thất có sẵn để mua.
- Tìm kiếm và lọc sản phẩm: Người dùng có thể tìm kiếm sản phẩm theo tên, danh mục, giá cả hoặc các thuộc tính khác.
- Xem chi tiết sản phẩm: Người dùng có thể xem thông tin chi tiết của sản phẩm bao gồm hình ảnh, mô tả, giá cả, và đánh giá của người dùng khác.
- Giỏ hàng: Người dùng có thể thêm sản phẩm vào giỏ hàng và quản lý số lượng sản phẩm trong giỏ hàng.
- Thanh toán: Ứng dụng cung cấp giao diện để người dùng thanh toán sản phẩm trong giỏ hàng thông qua các phương thức thanh toán khác nhau.
- Quản lý đơn hàng: Người dùng có thể xem và quản lý các đơn hàng đã đặt.

### 2. Công nghệ sử dụng:

- Backend/API: Sử dụng Spring Boot để xây dựng các API cung cấp dữ liệu và chức năng cho ứng dụng. Sử dụng MongoDB để lưu trữ dữ liệu.
- Frontend: Sử dụng Android Studio và Java để phát triển ứng dụng di động Android. Sử dụng các thư viện và giao thức như Retrofit, OkHttp, Gson để giao tiếp với các API từ phía backend.

### 3. Kiến trúc hệ thống:

- Backend/API: Sử dụng kiến trúc RESTful để thiết kế các API, cho phép người dùng truy cập và tương tác với các tài nguyên thông qua các yêu cầu HTTP.
- Frontend: Phát triển ứng dụng di động Android với mô hình kiến trúc MVVM (Model-View-ViewModel) để tách biệt logic xử lý và giao diện người dùng.
- Cơ sở dữ liệu: Sử dụng MongoDB làm cơ sở dữ liệu NoSQL để lưu trữ thông tin về sản phẩm, người dùng và đơn hàng.

### 4. Tính năng bổ sung (tùy chọn):

- Đánh giá và nhận xét: Cho phép người dùng đánh giá và viết nhận xét về sản phẩm đã Đặt hàng
- Theo dõi đơn hàng: Người dùng có thể đặt hàng và theo dõi trạng thái đơn hàng của mình, từ khi đặt hàng cho đến khi nhận hàng.
   Quản lý tài khoản: Người dùng có thể cập nhật thông tin cá nhân, thay đổi mật khẩu và xem lịch sử giao dịch.
   Bảo mật và xác thực:
- Xác thực người dùng: Sử dụng cơ chế đăng nhập và xác thực để bảo vệ thông tin người dùng và đảm bảo chỉ người dùng được phép truy cập vào các chức năng quản lý và thanh toán.
- Mã hóa dữ liệu: Đảm bảo rằng dữ liệu người dùng và thông tin giao dịch được mã hóa để tránh rủi ro bị đánh cắp thông tin.
   Phát triển và triển khai:
- Phát triển backend/API: Sử dụng Spring Boot để xây dựng các API, cùng với Hibernate/JPA để thao tác với cơ sở dữ liệu.
- Phát triển frontend: Sử dụng Android Studio và Java để phát triển ứng dụng di động Android, kết hợp các thư viện hỗ trợ giao tiếp với API.
   Triển khai ứng dụng:
- Dự án bán đồ nội thất sử dụng công nghệ Spring Boot và MongoDB cho phép người dùng trải nghiệm mua sắm thuận tiện và dễ dàng trên nền tảng di động Android, với việc sử dụng cơ sở dữ liệu NoSQL linh hoạt và khả năng mở rộng cao của MongoDB.

### Kiến trúc trong môi truong phát triển ứng dụng

![image](https://github.com/HNToan7202/E-ecomerce-app/assets/106101425/b94ae0ba-8b06-4fc2-b7a0-5dba164533e7)

<p align="right">(<a href="#top">back to top</a>)</p>
