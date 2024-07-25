package com.baridmedia.dto.request;

import com.baridmedia.dto.DocumentDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Data
@SuperBuilder
public class DocumentCreateRequest extends DocumentDto {
}
