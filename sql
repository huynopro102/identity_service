INSERT INTO public."permission" ("name", code, description, is_active) VALUES
('CREATE_POST', 'create_post', 'Quyền tạo bài viết', true),
('EDIT_POST', 'edit_post', 'Quyền chỉnh sửa bài viết', true),
('DELETE_POST', 'delete_post', 'Quyền xóa bài viết', true),
('PUBLISH_POST', 'publish_post', 'Quyền xuất bản bài viết', true),
('COMMENT_POST', 'comment_post', 'Quyền bình luận bài viết', true),
('LIKE_POST', 'like_post', 'Quyền thích bài viết', true),
('VIEW_ANALYTICS', 'view_analytics', 'Quyền xem thống kê trang blog', true),
('MANAGE_USERS', 'manage_users', 'Quyền quản lý người dùng', true),
('MODERATE_COMMENTS', 'moderate_comments', 'Quyền kiểm duyệt bình luận', true),
('UPLOAD_MEDIA', 'upload_media', 'Quyền upload hình ảnh và video', true);

INSERT INTO public."role" (name, description) VALUES
('ADMIN', 'Quản trị viên toàn quyền'),
('EDITOR', 'Biên tập viên, có quyền quản lý bài viết và hình ảnh'),
('AUTHOR', 'Tác giả, có quyền tạo và chỉnh sửa bài viết của mình'),
('CONTRIBUTOR', 'Người đóng góp, có quyền viết bài nhưng không xuất bản'),
('SUBSCRIBER', 'Người đăng ký, có quyền bình luận và thích bài viết'),
('USER', 'Người dùng thông thường'),
('STAFF', 'Nhân viên hỗ trợ với quyền hạn giới hạn');


-- ADMIN: có tất cả các quyền
INSERT INTO public.role_permissions (role_name, permission_name) VALUES
('ADMIN', 'CREATE_POST'),
('ADMIN', 'EDIT_POST'),
('ADMIN', 'DELETE_POST'),
('ADMIN', 'PUBLISH_POST'),
('ADMIN', 'COMMENT_POST'),
('ADMIN', 'LIKE_POST'),
('ADMIN', 'VIEW_ANALYTICS'),
('ADMIN', 'MANAGE_USERS'),
('ADMIN', 'MODERATE_COMMENTS'),
('ADMIN', 'UPLOAD_MEDIA');

-- EDITOR: có các quyền liên quan đến quản lý bài viết và media
INSERT INTO public.role_permissions (role_name, permission_name) VALUES
('EDITOR', 'CREATE_POST'),
('EDITOR', 'EDIT_POST'),
('EDITOR', 'PUBLISH_POST'),
('EDITOR', 'COMMENT_POST'),
('EDITOR', 'LIKE_POST'),
('EDITOR', 'MODERATE_COMMENTS'),
('EDITOR', 'UPLOAD_MEDIA');

-- AUTHOR: có quyền tạo bài viết, chỉnh sửa bài viết của mình
INSERT INTO public.role_permissions (role_name, permission_name) VALUES
('AUTHOR', 'CREATE_POST'),
('AUTHOR', 'EDIT_POST'),
('AUTHOR', 'COMMENT_POST'),
('AUTHOR', 'LIKE_POST'),
('AUTHOR', 'UPLOAD_MEDIA');

-- CONTRIBUTOR: chỉ có quyền viết bài và bình luận
INSERT INTO public.role_permissions (role_name, permission_name) VALUES
('CONTRIBUTOR', 'CREATE_POST'),
('CONTRIBUTOR', 'COMMENT_POST'),
('CONTRIBUTOR', 'LIKE_POST');

-- SUBSCRIBER: chỉ có quyền bình luận và thích bài viết
INSERT INTO public.role_permissions (role_name, permission_name) VALUES
('SUBSCRIBER', 'COMMENT_POST'),
('SUBSCRIBER', 'LIKE_POST');

-- USER: tương tự SUBSCRIBER
INSERT INTO public.role_permissions (role_name, permission_name) VALUES
('USER', 'COMMENT_POST'),
('USER', 'LIKE_POST');

-- STAFF: có quyền hỗ trợ một số thao tác
INSERT INTO public.role_permissions (role_name, permission_name) VALUES
('STAFF', 'CREATE_POST'),
('STAFF', 'EDIT_POST'),
('STAFF', 'COMMENT_POST'),
('STAFF', 'LIKE_POST'),
('STAFF', 'UPLOAD_MEDIA');


-- Chèn dữ liệu mẫu cho 200 user sử dụng generate_series
INSERT INTO public.users (id, created_at, deleted_at, dob, email, email_verified, failed_login_attempts, last_login_at, "password", password_reset_expires, password_reset_token, profile_image, status, updated_at, username)
SELECT
    'user' || LPAD(g::text, 3, '0') as id,
    now(),
    NULL,
    current_date - ((20 + (random() * 30)::int) * interval '1 year'),
    'user' || LPAD(g::text, 3, '0') || '@example.com',
    (random() < 0.8),
    (floor(random() * 5))::int,
    now() - ((floor(random() * 10))::int * interval '1 day'),
    'password' || g,  -- lưu ý: password cần được mã hóa trong ứng dụng thật
    now() + interval '1 day',
    NULL,
    'profile' || g || '.png',
    CASE
       WHEN random() < 0.9 THEN 'ACTIVE'
       WHEN random() < 0.5 THEN 'INACTIVE'
       ELSE 'BANNED'
    END,
    now(),
    'user' || LPAD(g::text, 3, '0')
FROM generate_series(1, 200) g;


-- Chèn dữ liệu user_roles cho 200 user
INSERT INTO public.user_roles (user_id, role_name)
SELECT
    'user' || LPAD(g::text, 3, '0'),
    CASE
       WHEN g % 7 = 1 THEN 'ADMIN'
       WHEN g % 7 = 2 THEN 'EDITOR'
       WHEN g % 7 = 3 THEN 'AUTHOR'
       WHEN g % 7 = 4 THEN 'CONTRIBUTOR'
       WHEN g % 7 = 5 THEN 'SUBSCRIBER'
       WHEN g % 7 = 6 THEN 'USER'
       ELSE 'STAFF'
    END as role_name
FROM generate_series(1, 200) g;






