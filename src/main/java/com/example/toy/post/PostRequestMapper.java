package com.example.toy.post;

import com.example.toy.post.dto.req.read.PostReadAllRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostRequestMapper {

  PostRequestMapper INSTANCE = Mappers.getMapper(PostRequestMapper.class);

  PostReadAllRequestDto toPostReadAllRequestDto(String isDeleted);

  PostReadAllRequestDto toPostReadAllRequestDto(
      PostReadAllRequestDto postReadAllRequestDto,
      String isDeleted,
      Integer pageNo,
      Integer pageRow,
      Integer totalCount);
}
