ALTER TABLE product ADD active tinyint;
update product set active = 1;