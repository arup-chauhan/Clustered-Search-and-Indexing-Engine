package com.engine.mapper;

import com.engine.dto.TagDto;
import com.engine.metadata.Tag;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-08T04:06:53-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Oracle Corporation)"
)
@Component
public class TagMapperImpl implements TagMapper {

    @Override
    public TagDto toDto(Tag entity) {
        if ( entity == null ) {
            return null;
        }

        TagDto tagDto = new TagDto();

        tagDto.setId( entity.getId() );
        tagDto.setName( entity.getName() );

        return tagDto;
    }

    @Override
    public Tag toEntity(TagDto dto) {
        if ( dto == null ) {
            return null;
        }

        Tag tag = new Tag();

        tag.setId( dto.getId() );
        tag.setName( dto.getName() );

        return tag;
    }

    @Override
    public List<TagDto> toDtoList(List<Tag> entities) {
        if ( entities == null ) {
            return null;
        }

        List<TagDto> list = new ArrayList<TagDto>( entities.size() );
        for ( Tag tag : entities ) {
            list.add( toDto( tag ) );
        }

        return list;
    }
}
