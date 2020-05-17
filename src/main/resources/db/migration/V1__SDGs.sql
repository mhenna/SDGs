#Admin table
insert into admin (id,email, password) values
(0,'admin0@test.com', 'admin0'),
(1,'admin1@test.com', 'admin1'),
(2,'admin2@test.com', 'admin2'),
(3,'admin3@test.com', 'admin3');


#Members table
insert into member (id,email, phone_number) values
(0,'u1@member.com', '202-555-0143'),
(1,'u2@member.com', '202-555-0180'),
(2,'u3@member.com', '202-555-0128'),
(3,'u4@member.com', '202-555-0174');


#privateSectors table
insert into private_sector (email,is_approved,name,password) values
('ps0@dell.com', 1,'Dell','ps0'),
('ps1@apple.com', 0,'Apple','ps1'),
('ps2@microsoft.com', 1,'Microsoft','ps2'),
('ps3@ibm.com', 0,'Ibm','ps3');


#project table
insert into project (aim,duration,name ,people_targeted) values
('Education', 365,'project1',6500),
('ZeroHunger', 90,'project2',7900),
('Peace', 60,'project3',1000),
('GenderEquality', 30,'project4',1000000);

#intendedSDGs table
insert into intendedsdg (name) values
('Education'),
('ZeroHunger'),
('GenderEquality');

#resources table
insert into resource (name) values
('resource0'),
('resource1'),
('resource2'),
('resource3');


#workLocation table
insert into work_location (area) values
('Cairo'),
('Giza'),
('Alexandria');


#direction_to_impact table
insert into direction_to_impact (name) values
('d1'),
('d2'),
('d3'),
('d4');

