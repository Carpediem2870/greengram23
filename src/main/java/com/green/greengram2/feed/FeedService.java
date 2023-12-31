package com.green.greengram2.feed;

import com.green.greengram2.ResVo;
import com.green.greengram2.feed.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedService {
    private final FeedMapper mapper;
    private final FeedPicsMapper picsMapper;
    private final FeedFavMapper favMapper;
    private final FeedCommentMapper commentMapper;

    public ResVo postFeed(FeedInsDto dto) {
        if (dto.getPics().size() == 0) {
            return new ResVo(2); //사진이 하나도 없음
        }
        FeedInsProcDto pDto = FeedInsProcDto.builder()
                .iuser(dto.getIuser())
                .contents(dto.getContents())
                .location(dto.getLocation())
                .pics(dto.getPics())
                .build();
        int feedAffectedRows = mapper.insFeed(pDto);
        if (feedAffectedRows == 0 || pDto.getIfeed() == 0) {
            return new ResVo(0);
        }
        int feedPicsAffectedRows = picsMapper.insFeedPics(pDto);
        if (feedPicsAffectedRows != dto.getPics().size()) { //트랜잭션이면 rollback
            return new ResVo(3); //사진 등록이 제대로 안 됨.
        }
        return new ResVo(pDto.getIfeed());
    }

    public List<FeedSelVo> getFeedAll(FeedSelDto dto) {
        List<FeedSelVo> list = mapper.selFeedAll(dto); //list는 null은 아니다.
        for (FeedSelVo vo : list) {
            vo.setPics(picsMapper.selFeedPicsAll(vo.getIfeed()));
        }
        return list;
    }

    public ResVo toggleFeedFav(FeedFavDto dto) {
        int result = favMapper.delFeedFav(dto);
        if (result == 0) {
            return new ResVo(favMapper.insFeedFav(dto));
        }
        return new ResVo(0);
    }
    public ResVo insComment(FeedCommentInsDto dto) {
        try{
            return new ResVo(commentMapper.insComment(dto));
        }catch (Exception e) {
            return new ResVo(0);
        }
    }

    public List<FeedCommentInsDto> selCommentAll(FeedCommentInsDto dto){
        List<FeedCommentInsDto> list = commentMapper.selCommentAll(dto);
        return list;
    }
}
