-- Table user_info : dummy sync from authentication server when user register (KeyCloak)
insert into `user_info`(`name`, `email`, `phone`)
values ('Thanh Tung', 'thanhtung0201@gmail.com', '0367123823');
insert into `user_info`(`name`, `email`, `phone`)
values ('Neeraj Bezalwar', 'nbezalwar@translations.com', '097766633');
insert into `user_info`(`name`, `email`, `phone`)
values ('Luna', 'thanhtung0202@gmail.com', '0988474565');

-- Table product : dummy data, assume it is create from back office or sync from product services
insert into `products` (`code`, `name`, `price`)
values ('ZOYVIWYZ84', 'Lexus ES 250', 65000);
insert into `products` (`code`, `name`, `price`)
values ('O18H2BSWJK', 'BMW X3', 80000);
insert into `products` (`code`, `name`, `price`)
values ('S2BWIGHTG2', 'Honda CRV', 40000);
insert into `products` (`code`, `name`, `price`)
values ('T4IKA84DWC', 'Toyota Cross', 30000);