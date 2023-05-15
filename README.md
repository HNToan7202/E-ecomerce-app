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

## Prerequisites

Trước khi cài đặt và chỉnh sửa web app, hãy chắc rằng trên máy đã cài đặt:

* Công cụ hỗ trợ lập trình: Android Studio (khuyên dùng), hoặc bất kì công cụ khác có tích hợp môi trường phát triển Java (IDE) khác.
* Phần mềm web server: Spring Tool Suite 
* Hệ quản trị cơ sở dữ liệu: MongoDB NoSQL (khuyên dùng).

## Mô tả dự án:

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
### SCREEN SHOT
![z4348266923299_5b8f5b3f210fd97845dd1ad86a4d1cc7](https://github.com/HNToan7202/E-ecomerce-app/assets/106101425/fb322c80-f53e-400a-8de5-5ae70a8ff67d){:width="400px" height="300px"}
![z4348266930141_f31806ab12c4e02445faa5310db43808](https://github.com/HNToan7202/E-ecomerce-app/assets/106101425/e2bdfe41-eb63-4a73-b99e-83150a6945eb){:width="400px" height="300px"}
![z4348266910542_9e396e3b6a9f6d55eadff0004d633ab8](https://github.com/HNToan7202/E-ecomerce-app/assets/106101425/bb95bfa4-29c8-4454-9aad-0aeeea10d504){:width="400px" height="300px"}
![z4348266911131_89ff54892a7d7316baa5fc5d8bc4941c](https://github.com/HNToan7202/E-ecomerce-app/assets/106101425/f3aff56c-8f45-42db-991a-07eac0b38859){:width="400px" height="300px"}
![z4348266908327_1ee5a9c159a41f4fa8b8fcc64b5bf6f0](https://github.com/HNToan7202/E-ecomerce-app/assets/106101425/438415ba-3220-4026-9367-7b8a782a167a)

![image](https://github.com/HNToan7202/E-ecomerce-app/assets/106101425/97094118-6e9a-407d-bc2d-9e8fbb787a95){:width="400px" height="300px"}
![z4348266917947_c8a97df09ae8954e548d6160a772128f](https://github.com/HNToan7202/E-ecomerce-app/assets/106101425/7c4da562-dc04-4c2e-a3f0-08495ef5afb2){:width="400px" height="300px"}
![z4348266919794_b8ba0d0bf4032ae962fcc84fe578f4dc](https://github.com/HNToan7202/E-ecomerce-app/assets/106101425/5242a68d-96ae-4178-b88c-56b6ba665800){:width="400px" height="300px"}
![z4348266918394_1ac1b5005391d6ee735a79e1f5ba5ac6](https://github.com/HNToan7202/E-ecomerce-app/assets/106101425/27d29e6c-7bdc-4a2d-bbf1-42f8508ae625){:width="400px" height="300px"}
![z4348266924445_09fc2f8aa5342b823247fd5bb7b6114b](https://github.com/HNToan7202/E-ecomerce-app/assets/106101425/47baec75-7f09-4852-9e21-9a2bb8b780e6){:width="400px" height="300px"}
![z4348266928362_86e47d682b6fa3253732bc5584fad0b3](https://github.com/HNToan7202/E-ecomerce-app/assets/106101425/d2631b8f-4840-43d1-995d-0961d6f17cd7){:width="400px" height="300px"}
![z4348266915286_eaedf8393eae1f80fd8112f9b5b9a4e0](https://github.com/HNToan7202/E-ecomerce-app/assets/106101425/c8e2058b-6b72-422e-b87f-1920eebadec3){:width="400px" height="300px"}
![z4348266919935_c9dc793c87b91b7f24815ee916bca146](https://github.com/HNToan7202/E-ecomerce-app/assets/106101425/53017a07-352d-4d0f-a9ef-8e508c7686ce){:width="400px" height="300px"}
![z4348266918853_29e123108df93bdf8f18c4a9ee019473](https://github.com/HNToan7202/E-ecomerce-app/assets/106101425/6acccc09-201e-4716-b633-ad6d274b08f8){:width="400px" height="300px"}

![z4348266922069_893a8c33a5c72d57c958952aa236e0ff](https://github.com/HNToan7202/E-ecomerce-app/assets/106101425/1497fd76-8ff8-49b2-9ab0-bfddb4f37a09){:width="400px" height="300px"}

![z4348266922627_1b7bfce6d3f7fad7a3932f12eb6e850a](https://github.com/HNToan7202/E-ecomerce-app/assets/106101425/8e8bfdfd-1f76-48dd-a72d-495520ccc287){:width="400px" height="300px"}
![z4348266903856_aac4bdd53b3c7c237717de6815153342](https://github.com/HNToan7202/E-ecomerce-app/assets/106101425/2f50e2e6-6c6d-49c9-acca-0a55851f7a2e){:width="400px" height="300px"}
![z4348266913737_cac58f437addb8c36f2e61fc1664e1fb](https://github.com/HNToan7202/E-ecomerce-app/assets/106101425/af22bc4a-d08d-4fd2-b8d6-13de3fb3b630){:width="400px" height="300px"}



<p align="right">(<a href="#top">back to top</a>)</p>
