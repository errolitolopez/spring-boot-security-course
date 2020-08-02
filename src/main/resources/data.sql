INSERT INTO `user` 
    (`user_id`,`email`, `last_name`, `first_name`, `username`, `password`)
VALUES
    (1, 'errolitolopez@gmail.com', 'lopez', 'errolito', 'rols09', '$2a$09$fC0nA3Qz2RNf2aU92o9f5OtLdu8UyAs0cjse/0uUb4.y2NCbJ8Uqa');
    
INSERT INTO `role` (`role_id`, `role`) VALUES (1,'ADMIN');
INSERT INTO `role` (`role_id`, `role`) VALUES (2,'ADMIN_TRAINEE');
INSERT INTO `role` (`role_id`, `role`) VALUES (3,'STUDENT');
    
INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES (1, 1);
