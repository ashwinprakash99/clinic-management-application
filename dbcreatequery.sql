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
	id bigint primary key,
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
	med_pres_id bigint not null,
	consultation_fee int not null,
	total_fee float not null,
	created_at datetime not null,
	foreign key(med_pres_id) references Medicine_Prescription(id) on update cascade on delete cascade
);
alter table Billing auto_increment = 0;
