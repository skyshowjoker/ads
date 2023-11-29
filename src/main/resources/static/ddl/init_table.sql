create database if not exists ads;

use ads;

CREATE table IF NOT EXISTS `patient`(
   `id` INT UNSIGNED AUTO_INCREMENT,
   `name` VARCHAR(100) NOT NULL,
   `gender` VARCHAR(40) NOT NULL,
   `birthday` DATE,
   `case_id` VARCHAR(100) NOT NULL,
   `phone` VARCHAR(20) NOT NULL,
   `email` VARCHAR(100) NOT NULL,
   `description` VARCHAR(1000) NOT NULL,
   PRIMARY KEY ( `id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS `fileinfo`(
   `id` INT UNSIGNED AUTO_INCREMENT,
   `patientId` INT,
   `uploadDate` DATE,
   `filePath` VARCHAR(200),
   `emzlResult` VARCHAR(100),
   `progressiveResult` VARCHAR(100),
   `activeInd` VARCHAR(1),
   PRIMARY KEY ( `id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;




insert into patient values (1, 'Wu, Fang', 'Female', '1978-06-14', '10223723', '15283476741', '993768942@qq.com', 'The right eyeball is slightly protruding, the right orbital floor wall is partially boneless, and the right lacrimal sac and nasolacrimal duct are seen with soft tissue density shadows with clear edges.')

insert into patient values (2, 'Li, LiHong', 'Female', '1984-02-03', '10223723', '15283476741', '993768942@qq.com', 'No medical history, combined with clinical symptoms. Further enhance CT/MR examination if necessary')

insert into patient values (3, '董伟民', '男', '1963-01-24', '10223723', '15283476741', '993768942@qq.com', '[影像所见] 右侧眼球见稍突出，右眶底壁局部骨质缺如，右侧泪囊及鼻泪管见软组织密度影，边缘清楚，CT值约53Hu，左眶及眼球、眼外肌未见明显异常，双侧视神经未见明显异常。两侧上颌窦粘膜增厚。[影像所得] 右侧泪囊及鼻泪管占位？无病史，结合临床。必要时进一步增强CT/MR检查。')
