package com.engine.api.mapper;

import com.engine.dto.MetadataDto;
import com.engine.metadata.DocumentMeta;
import com.engine.metadata.Tag;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-08T04:06:53-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Oracle Corporation)"
)
@Component
public class MetadataMapperImpl implements MetadataMapper {

    @Override
    public MetadataDto toDto(DocumentMeta entity) {
        if ( entity == null ) {
            return null;
        }

        MetadataDto metadataDto = new MetadataDto();

        metadataDto.setTags( tagSetToStringList( entity.getTags() ) );
        metadataDto.setId( entity.getId() );
        metadataDto.setTitle( entity.getTitle() );
        metadataDto.setCreatedAt( entity.getCreatedAt() );

        return metadataDto;
    }

    @Override
    public DocumentMeta toEntity(MetadataDto dto) {
        if ( dto == null ) {
            return null;
        }

        DocumentMeta documentMeta = new DocumentMeta();

        documentMeta.setTags( stringListToTagSet( dto.getTags() ) );
        documentMeta.setId( dto.getId() );
        documentMeta.setTitle( dto.getTitle() );
        documentMeta.setCreatedAt( dto.getCreatedAt() );

        return documentMeta;
    }

    protected List<String> tagSetToStringList(Set<Tag> set) {
        if ( set == null ) {
            return null;
        }

        List<String> list = new ArrayList<String>( set.size() );
        for ( Tag tag : set ) {
            list.add( mapTagToString( tag ) );
        }

        return list;
    }

    protected Set<Tag> stringListToTagSet(List<String> list) {
        if ( list == null ) {
            return null;
        }

        Set<Tag> set = new LinkedHashSet<Tag>( Math.max( (int) ( list.size() / .75f ) + 1, 16 ) );
        for ( String string : list ) {
            set.add( mapStringToTag( string ) );
        }

        return set;
    }
}
