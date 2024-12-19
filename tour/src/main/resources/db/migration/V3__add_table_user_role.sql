CREATE TABLE IF NOT EXISTS user_role (
    id UUID PRIMARY KEY,
    user_id UUID,
    role_id UUID,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES account (id),
    FOREIGN KEY (role_id) REFERENCES role (id)
);