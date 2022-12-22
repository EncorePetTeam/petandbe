package com.encore.petandbe.controller.accommodation.image.file.api;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.*;
import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.encore.petandbe.controller.accommodation.image.file.responses.ImageFileListCreateResponse;
import com.encore.petandbe.interceptor.PermissionInterceptor;
import com.encore.petandbe.model.user.user.Role;
import com.encore.petandbe.service.accommodation.image.file.ImageFileService;

@WebMvcTest(controllers = ImageFileController.class)
@AutoConfigureRestDocs
class ImageFileControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ImageFileService imageFileService;

	@MockBean
	private PermissionInterceptor permissionInterceptor;

	private final Long userId = 1L;

	@Test
	@DisplayName("Store Image Files Controller Test - Success")
	void storeImageFilesSuccess() throws Exception {
		//given
		String mockImageFileName = "Test Image File.png";
		MockMultipartFile multipartFile = new MockMultipartFile(mockImageFileName, mockImageFileName.getBytes());
		MockMultipartFile[] mockMultipartFiles = {multipartFile};

		List<String> imageFileUrlList = new ArrayList<>();
		imageFileUrlList.add(mockImageFileName);
		ImageFileListCreateResponse imageFileListCreateResponse = new ImageFileListCreateResponse(imageFileUrlList);

		when(imageFileService.storeAndSaveImageFiles(any())).thenReturn(
			imageFileListCreateResponse);
		when(permissionInterceptor.preHandle(any(), any(), any())).thenReturn(true);

		//when
		ResultActions resultActions = mockMvc.perform(multipart("/image-file")
			.file("file", multipartFile.getBytes())
			.contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
			.accept(MediaType.APPLICATION_JSON)
			.requestAttr(Role.USER.getValue(), userId.intValue()));
		//then
		resultActions.andExpect(status().isCreated())
			.andDo(print())
			.andDo(document("accommodation-retrieval",
				responseFields(
					fieldWithPath("imageUrlList").type(JsonFieldType.ARRAY).description("등록된 이미지 Url 리스트")
				)
			));
	}
}