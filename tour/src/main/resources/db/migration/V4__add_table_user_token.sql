CREATE TABLE IF NOT EXISTS user_token (
    id UUID PRIMARY KEY,
    user_id UUID,
    access_token TEXT,
    refresh_token UUID,
    refresh_token_expire TIMESTAMP,
    is_refreshed BOOLEAN,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES account (id) ON DELETE SET NULL
);