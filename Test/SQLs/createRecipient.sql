/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  sawthiha
 * Created: Aug 26, 2017
 */

create table tblRecipient(
    id int not null generated always as identity(start with 0, increment by 1),
    voucher int not null,
    recipient varchar(50) not null,
    address varchar(60) not null,
    amount bigint default 0,
    plus int default 0,
    minus int default 0,

    primary key(id),
    foreign key(voucher) references tblVoucher(id)
    on delete cascade
);