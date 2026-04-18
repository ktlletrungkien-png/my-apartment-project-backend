use estatebasic;
INSERT INTO `role` (`name`, `code`) VALUES ('Khách hàng', 'customer');

INSERT INTO `user` (`username`, `password`, `fullname`, `email`, `status`)
VALUES ('customer01', '123456', 'Khách hàng 01', 'customer01@gmail.com', 1);

INSERT INTO `user_role` (`roleid`, `userid`)
VALUES (
  (SELECT `id` FROM `role` WHERE `code` = 'customer'),
  (SELECT `id` FROM `user` WHERE `username` = 'customer01')
);
update building set image='/images/Nam Giao.jpg' where id=1;
update building set image='/images/ACM_Building.jpg' where id=2;
update building set image='/images/alpha_tower.jpg' where id=3;
update building set image='/images/ido_building.jpg' where id=4;