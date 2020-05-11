drop table IF EXISTS post;
create TABLE post(
  id INT PRIMARY KEY Auto_Increment,
  summary VARCHAR(100) NOT NULL,
  detail text NOT NULL,
  gmt_create BIGINT NOT NULL,
  gmt_modified BIGINT NOT NULL,
  creator INT,
  comment_count INT default 0,
  like_count INT default 0,
  tag VARCHAR(256)
);
insert into USER(
    summary,
    detail,
    gmt_create,
    gmt_modified,
    creator,
    comment_count,
    like_count,
    tag
  )
VALUES
  (
    #{summary}, #{detail}, #{gmtCreate}, #{gmtModified},
    #{creator},
    #{comment_count},
    #{like_count},
    #{tag}
  )
