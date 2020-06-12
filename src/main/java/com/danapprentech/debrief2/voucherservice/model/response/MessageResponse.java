package com.danapprentech.debrief2.voucherservice.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import javax.persistence.Column;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponse
{
	private String message;
	private String status;
	private String path;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date timestamp;
}
