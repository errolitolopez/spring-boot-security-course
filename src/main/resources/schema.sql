DROP TABLE IF EXISTS `users_roles`;
DROP TABLE IF EXISTS `role`;
DROP TABLE IF EXISTS `user`;

CREATE TABLE user (
    `user_id` int(11) not null primary key auto_increment,
    `email` varchar(64) not null,
    `last_name` varchar(64) not null,
    `first_name` varchar(64) not null,
    `username` varchar(64) not null,
    `password` varchar(64) not null
);


CREATE TABLE `role` (
    `role_id` int(11) not null primary key auto_increment,
    `role` varchar(64)
);


CREATE TABLE `users_roles` (
    `user_id` int(11) not null,
    `role_id` int(11) not null,
    primary key (`user_id`, `role_id`),
    constraint `users_fk` foreign key (`user_id`) references `user` (`user_id`),
    constraint `roles_fk` foreign key (`role_id`) references `role` (`role_id`)
);
