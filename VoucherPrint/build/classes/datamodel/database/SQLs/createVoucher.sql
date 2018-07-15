/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  sawthiha
 * Created: Aug 26, 2017
 */

create table tblVoucher(
    id int not null generated always as identity(start with 0,increment by 1),
    customer char(80) not null,
    created_date timestamp not null,
    phone char(20) not null,
    printed boolean not null,

    primary key(id)
);