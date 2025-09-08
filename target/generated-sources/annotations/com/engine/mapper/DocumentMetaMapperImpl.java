package com.engine.mapper;

import com.engine.dto.DocumentMetaDto;
import com.engine.dto.TagDto;
import com.engine.metadata.DocumentMeta;
import com.engine.metadata.Tag;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-08T04:06:53-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Oracle Corporation)"
)
@Component
public class DocumentMetaMapperImpl implements DocumentMetaMapper {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public DocumentMetaDto toDto(DocumentMeta entity) {
        if ( entity == null ) {
            return null;
        }

        DocumentMetaDto documentMetaDto = new DocumentMetaDto();

        documentMetaDto.setTags( tagSetToTagDtoList( entity.getTags() ) );
        documentMetaDto.setId( entity.getId() );
        documentMetaDto.setTitle( entity.getTitle() );
        documentMetaDto.setContent( entity.getContent() );
        documentMetaDto.setCreatedAt( entity.getCreatedAt() );

        return documentMetaDto;
    }

    @Override
    public DocumentMeta toEntity(DocumentMetaDto dto) {
        if ( dto == null ) {
            return null;
        }

        DocumentMeta documentMeta = new DocumentMeta();

        documentMeta.setTags( tagDtoListToTagSet( dto.getTags() ) );
        documentMeta.setId( dto.getId() );
        documentMeta.setTitle( dto.getTitle() );
        documentMeta.setContent( dto.getContent() );
        documentMeta.setCreatedAt( dto.getCreatedAt() );

        return documentMeta;
    }

    @Override
    public List<DocumentMetaDto> toDtoList(List<DocumentMeta> entities) {
        if ( entities == null ) {
            return null;
        }

        List<DocumentMetaDto> list = new ArrayList<DocumentMetaDto>( entities.size() );
        for ( DocumentMeta documentMeta : entities ) {
            list.add( toDto( documentMeta ) );
        }

        return list;
    }

    protected List<TagDto> tagSetToTagDtoList(Set<Tag> set) {
        if ( set == null ) {
            return null;
        }

        List<TagDto> list = new ArrayList<TagDto>( set.size() );
        for ( Tag tag : set ) {
            list.add( tagMapper.toDto( tag ) );
        }

        return list;
    }

    protected Set<Tag> tagDtoListToTagSet(List<TagDto> list) {
        if ( list == null ) {
            return null;
        }

        Set<Tag> set = new LinkedHashSet<Tag>( Math.max( (int) ( list.size() / .75f ) + 1, 16 ) );
        for ( TagDto tagDto : list ) {
            set.add( tagMapper.toEntity( tagDto ) );
        }

        return set;
    }
}
