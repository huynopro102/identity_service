CREATE TABLE user_sessions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,                    -- FK đến bảng users
    device_info VARCHAR(255),                   -- Thông tin thiết bị (ví dụ: "iPhone 14, Safari")
    ip_public VARCHAR(45),                     -- Địa chỉ IP Public của router
    ip_private VARCHAR(45),                    -- Địa chỉ IP Private của thiết bị
    port INT,                                  -- Port nguồn NAT (nếu có)
    network_name VARCHAR(255),                 -- Tên mạng Wi-Fi hoặc SSID (nếu có)
    login_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Thời gian đăng nhập
    logout_time TIMESTAMP,                      -- Thời gian đăng xuất
    token VARCHAR(255),                         -- Token cho phiên đăng nhập (JWT hoặc session ID)
    is_active BOOLEAN DEFAULT TRUE,             -- Trạng thái phiên đăng nhập (còn hoạt động hay không)
    user_agent TEXT,                            -- User-Agent của thiết bị
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
