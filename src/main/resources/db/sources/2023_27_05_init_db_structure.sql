CREATE TABLE `user_info`
(
    user_id      INT                                 NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name         VARCHAR(150)                        NOT NULL,
    phone        VARCHAR(15)                         NOT NULL,
    email        VARCHAR(100)                        NOT NULL,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE `products`
(
    product_id   INT                                 NOT NULL PRIMARY KEY AUTO_INCREMENT,
    code         VARCHAR(30)                         NOT NULL UNIQUE,
    name         VARCHAR(200)                        NOT NULL,
    price        DECIMAL                             NOT NULL,
    is_expired   TINYINT   DEFAULT 0, -- 1 mean expired
    updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE `orders`
(
    order_id         INT                                 NOT NULL PRIMARY KEY AUTO_INCREMENT,
    code             VARCHAR(30)                         NOT NULL UNIQUE,
    shipping_address TEXT                                NOT NULL,
    customer_id      INT,
    status           TINYINT, -- 1 : PLACED, 2 : CONFIRMED, 3 : SHIPPED, 4 : DELIVERED
    created_date     TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_date     TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES user_info (user_id),
    INDEX (status)
);

CREATE TABLE `order_product`
(
    order_id   INT NOT NULL,
    product_id INT NOT NULL,
    quantity   INT default 1,
    PRIMARY KEY (order_id, product_id),
    FOREIGN KEY (product_id) REFERENCES `products` (product_id),
    FOREIGN KEY (order_id) REFERENCES `orders` (order_id)
);

