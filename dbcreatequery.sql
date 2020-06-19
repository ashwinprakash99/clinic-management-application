create database clinic_management_application;
use clinic_management_application;

-- drop database clinic_management_application;

create table Patient (
	UHID bigint auto_increment primary key,
	patient_name varchar(100) not null,
	DOB date not null,
	gender char(1) not null,
	address varchar(100) not null,
	phone_number bigint not null,
	created_at datetime not null
);
alter table Patient auto_increment = 0;


create table Complaint (
	id bigint auto_increment primary key,
	patient_id bigint not null,
	complaint1 varchar(100) not null,
	complaint2 varchar(100),
	complaint3 varchar(100),
	explanation1 varchar(100) not null,
	explanation2 varchar(100),
	explanation3 varchar(100),
	created_at datetime not null,
	foreign key(patient_id) references Patient(UHID) on update cascade on delete cascade
);
alter table Complaint auto_increment = 0;


create table Medicine (
	id bigint auto_increment primary key,
	medicine_name varchar(100) not null,
	price float not null,
	tax float not null,
	quantity int not null,
	created_at datetime not null,
	modified_at datetime not null
);
alter table Medicine auto_increment = 0;


create table Medicine_Prescription (
	id bigint auto_increment primary key,
	complaint_id bigint not null,
	medicine_id bigint not null,
	quantity int not null,
	morning boolean not null,
	afternoon boolean not null,
	night boolean not null,
	cost float not null,
	created_at datetime not null,
	foreign key(complaint_id) references Complaint(id) on update cascade on delete cascade,
	foreign key(medicine_id) references Medicine(id) on update cascade on delete cascade
);
alter table Medicine_Prescription auto_increment = 0;


create table Treatment (
	id bigint auto_increment primary key,
	doc_name varchar(100) not null,
	description varchar(100) not null,
	complaint_id bigint not null,
	created_at datetime not null,
	foreign key(complaint_id) references Complaint(id) on update cascade on delete cascade
);
alter table Treatment auto_increment = 0;


create table Examination (
	id bigint auto_increment primary key,
	bp varchar(100),
	pulse varchar(100),
	temperature varchar(100),
	cvs varchar(100),
	rs varchar(100),
	pa varchar(100),
	cns varchar(100),
	labtest varchar(100),
	ecg varchar(100),
	x_ray varchar(100),
	ct_scan_mri varchar(100),
	two_d_echo varchar(100),
	tmt varchar(100),
	eeg varchar(100),
	diagnosis varchar(100),
	other varchar(100),
	complaint_id bigint not null,
	created_at datetime not null,
	foreign key(complaint_id) references Complaint(id) on update cascade on delete cascade
);
alter table Examination auto_increment = 0;


create table Billing (
	id bigint auto_increment primary key,
	complaint_id bigint not null,
	consultation_fee int not null,
	total_fee float not null,
	created_at datetime not null,
	foreign key(complaint_id) references Complaint(id) on update cascade on delete cascade
);
alter table Billing auto_increment = 0;


create table General_Billing (
	id bigint auto_increment primary key,
	name varchar(100) not null,
	cost float not null,
	created_at datetime not null
);
alter table General_Billing auto_increment = 0;


create table General_Medicine_Outlet (
	id bigint auto_increment primary key,
	bill_id bigint not null,
	medicine_id bigint not null,
	quantity int not null,
	cost float not null,
	created_at datetime not null
);
alter table General_Medicine_Outlet auto_increment = 0;


-- Triggers

create trigger med_pres_reduce_insert
after insert
on Medicine_Prescription for each row
update Medicine set quantity=quantity-new.quantity where id=new.medicine_id;


create trigger med_pres_reduce_before_update
before update
on Medicine_Prescription for each row
update Medicine set quantity=quantity+old.quantity where id=old.medicine_id;


create trigger med_pres_reduce_after_update
after update
on Medicine_Prescription for each row
update Medicine set quantity=quantity-new.quantity where id=new.medicine_id;



create trigger gen_med_pres_reduce_insert
after insert
on General_Medicine_Outlet for each row
update Medicine set quantity=quantity-new.quantity where id=new.medicine_id;


create trigger gen_med_pres_reduce_before_update
before update
on General_Medicine_Outlet for each row
update Medicine set quantity=quantity+old.quantity where id=old.medicine_id;


create trigger gen_med_pres_reduce_after_update
after update
on General_Medicine_Outlet for each row
update Medicine set quantity=quantity-new.quantity where id=new.medicine_id;

