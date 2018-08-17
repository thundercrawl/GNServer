select * from t_fundefficient where create_time between '2018-04-01' and '2018-04-31'

INSERT INTO t_fundefficient(fundSource,fundSum,fundUsage,monthlyFundSum,alreadyLentFundSum,LeftFundSum) VALUES ( 'china bank', 785432.21, 'eat',77321.2, 5632.21,32.1);
INSERT INTO `t_fundefficient`(fundSource,create_time) VALUES ( 'china bank','Mon Aug 06 21:08:31 CST 2018')
select * from t_fundefficient where create_time between '2018-04-01' and '2018-04-31'


select cp.permission_code from t_console_permission cp
left join t_console_user_permission p on p.permission_id = cp.id
left join t_console_user u on u.id = p.user_id
where u.user_name = 'admin'

  insert into  t_fundefficient(fund_Source) values('china bank')


delete from zst.t_kunpengcartype where id >= '6'
  