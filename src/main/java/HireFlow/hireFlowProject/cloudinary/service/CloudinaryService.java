package HireFlow.hireFlowProject.cloudinary.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import HireFlow.hireFlowProject.cloudinary.dto.UploadResult;

@Service
public class CloudinaryService {

    private final Cloudinary cloudinary;

    public CloudinaryService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public UploadResult uploadResume(MultipartFile file) throws IOException {

        Map<?, ?> result = cloudinary.uploader().upload(
                file.getBytes(),
                ObjectUtils.asMap(
                        "resource_type", "auto",
                        "folder", "hireflow/resumes"));

        return new UploadResult(
                result.get("secure_url").toString(),
                result.get("public_id").toString());
    }

    public UploadResult uploadProfilePicture(MultipartFile file) throws IOException {

        Map<?, ?> result = cloudinary.uploader().upload(
                file.getBytes(),
                ObjectUtils.asMap(
                        "folder", "hireflow/profile-pictures"));

        return new UploadResult(
                result.get("secure_url").toString(),
                result.get("public_id").toString());
    }

    public UploadResult uploadCompanyLogo(MultipartFile file) throws IOException {

        Map<?, ?> result = cloudinary.uploader().upload(
                file.getBytes(),
                ObjectUtils.asMap(
                        "folder", "hireflow/company-logos"));

        return new UploadResult(
                result.get("secure_url").toString(),
                result.get("public_id").toString());
    }

    public void deleteFile(String publicId) throws IOException {

        if (publicId == null || publicId.isBlank()) {
            return;
        }

        cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
    }
}