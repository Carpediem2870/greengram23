package com.green.greengram2.feed;

import com.green.greengram2.feed.model.FeedFavDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FeedFavMapper {
    int delFeedFav(FeedFavDto dto);
    int insFeedFav(FeedFavDto dto);
}
