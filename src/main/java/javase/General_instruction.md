1. Tiêu đề (Headers)
   Dùng ký tự # để tạo các cấp độ tiêu đề:

markdown
Copy code
# Tiêu đề cấp 1
## Tiêu đề cấp 2
### Tiêu đề cấp 3
#### Tiêu đề cấp 4
##### Tiêu đề cấp 5
###### Tiêu đề cấp 6
2. Định dạng văn bản
   In đậm:

markdown
Copy code
**Văn bản in đậm**
Kết quả:
Văn bản in đậm

In nghiêng:

markdown
Copy code
*Văn bản in nghiêng*
Kết quả:
Văn bản in nghiêng

Vừa in đậm vừa in nghiêng:

markdown
Copy code
***Văn bản vừa in đậm vừa in nghiêng***
Kết quả:
Văn bản vừa in đậm vừa in nghiêng

Gạch ngang:

markdown
Copy code
~~Văn bản gạch ngang~~
Kết quả:
Văn bản gạch ngang

3. Danh sách
   Danh sách không thứ tự:
   Dùng -, *, hoặc +.

markdown
Copy code
- Mục 1
- Mục 2
    - Mục con
      Danh sách có thứ tự:
      Dùng số kèm dấu chấm.

markdown
Copy code
1. Mục 1
2. Mục 2
    1. Mục con 1
    2. Mục con 2
4. Liên kết
   Liên kết thường:

markdown
Copy code
[Tên liên kết](https://example.com)
Kết quả:
Tên liên kết

Chèn URL trực tiếp:

markdown
Copy code
<https://example.com>
Kết quả:
https://example.com

5. Hình ảnh
   Chèn hình ảnh:

markdown
Copy code
![Alt text](https://example.com/image.png)
6. Code (Mã nguồn)
   Dòng mã đơn:

markdown
Copy code
`Code đơn giản`
Khối mã nhiều dòng:

markdown
Copy code
```ngôn_ngữ
// Code mẫu
console.log("Hello, World!");
Copy code
Ví dụ với Java:

markdown
Copy code
```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
7. Bảng
Tạo bảng:

markdown
Copy code
| Cột 1 | Cột 2 | Cột 3 |
|-------|-------|-------|
| Hàng 1 | Hàng 2 | Hàng 3 |
| Dữ liệu 1 | Dữ liệu 2 | Dữ liệu 3 |
8. Trích dẫn (Blockquotes)
markdown
Copy code
> Đây là trích dẫn
> > Trích dẫn lồng nhau
9. Danh sách việc cần làm
markdown
Copy code
- [ ] Công việc 1
- [x] Công việc 2 (Đã hoàn thành)
10. Dòng kẻ ngang
Dùng ba hoặc nhiều ký tự -, *, hoặc _:

markdown
Copy code
---