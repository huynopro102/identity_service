//$(document).ready(function(){
//	// Activate tooltip
//	$('[data-toggle="tooltip"]').tooltip();
//
//	// Select/Deselect checkboxes
//	var checkbox = $('table tbody input[type="checkbox"]');
//	$("#selectAll").click(function(){
//		if(this.checked){
//			checkbox.each(function(){
//				this.checked = true;
//			});
//		} else{
//			checkbox.each(function(){
//				this.checked = false;
//			});
//		}
//	});
//	checkbox.click(function(){
//		if(!this.checked){
//			$("#selectAll").prop("checked", false);
//		}
//	});
//});
// choose images

    document.addEventListener("DOMContentLoaded", () => {
        const image = document.getElementById("file-upload-trigger");
        const fileInput = document.getElementById("file-input");
        const preview_title = document.getElementById("preview_title");
        const upload_image = document.getElementById("upload_image")
        const btn_preview_large = document.getElementById("btn-preview-large")
        const large_preview = document.getElementById("large-preview")
        const btn_create = document.getElementById("create-btn")

        upload_image.addEventListener("click",(e)=>{
            fileInput.click()
        })

        // Hiển thị tên file được chọn lên thẻ img (làm title)
        fileInput.addEventListener("change", () => {
            if (fileInput.files.length > 0) {
                fileReader = new FileReader();
                fileReader.onload =  (e) =>{
                    image.src = e.target.result
                }
                fileReader.readAsDataURL(fileInput.files[0]); // Đọc file ảnh
                preview_title.textContent = fileInput.files[0].name;
                const fileName = fileInput.files[0].name;
                image.title = fileName; // Set original name vào title của ảnh
            }
        });

        // onclick preview image
        btn_preview_large.addEventListener("click",(e)=>{
            large_preview.src = image.src
        })

        // onclick button create
        btn_create.addEventListener("click",(e)=>{
            e.preventDefault() // ngăn chặn hành động mặc đinh cuả thẻ button
            document.getElementById("upload-file-form").submit()
        })
    });
