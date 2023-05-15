 package shop.mtcoding.village.jpa;

 import org.junit.jupiter.api.Assertions;
 import org.junit.jupiter.api.BeforeEach;
 import org.junit.jupiter.api.DisplayName;
 import org.junit.jupiter.api.Test;
 import org.junit.jupiter.api.extension.ExtendWith;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
 import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
 import org.springframework.test.context.junit.jupiter.SpringExtension;
 import org.springframework.transaction.annotation.Transactional;
 import shop.mtcoding.village.model.file.*;

 import java.util.List;
 import java.util.Optional;

 @DataJpaTest
 @ExtendWith(SpringExtension.class)
 @DisplayName("파일 JPA 테스트")
 public class FileRepositoryTest {

     @Autowired
     private FileRepository fileRepository;

     @Autowired
     private TestEntityManager entityManager;

     @BeforeEach
     public void init(){
         FileInfo fileInfo = setUpByFileInfo(FileType.PLACE);
         setUpByFile(fileInfo, "image", "jsadjwnqjkdnjskandjskandjka", FileStatus.WAIT);
     }

     @Test
     @Transactional
     @DisplayName("파일 조회")
     void selectAll() {
         List<File> files = fileRepository.findAll();
         Assertions.assertNotEquals(files.size(), 0);

         File file = files.get(0);
         Assertions.assertEquals(file.getFileName(), "사무실.jpg");
     }

     @Test
     @Transactional
     @DisplayName("파일 조회 및 수정")
     void selectAndUpdate() {
         var optionalFiles = this.fileRepository.findById(1L);

         if(optionalFiles.isPresent()) {
             var result = optionalFiles.get();
             Assertions.assertEquals(result.getFileName(),"사무실.jpg");

             var fileUrl = "jsadjwnqjkdnjskandjskandjka111";
             result.setFileUrl(fileUrl);
             File merge = entityManager.merge(result);

             Assertions.assertEquals(merge.getFileUrl(),"jsadjwnqjkdnjskandjskandjka111");
         } else {
             Assertions.assertNotNull(optionalFiles.get());
         }
     }

     @Test
     @Transactional
     @DisplayName("파일 삽입 및 삭제")
     void insertAndDelete() {
         FileInfo fileInfo = setUpByFileInfo(FileType.PLACE);
         File file = setUpByFile(fileInfo, "image", "jsadjwnqjkdnjskandjskandjka", FileStatus.WAIT);
         Optional<File> findFile = this.fileRepository.findById(file.getId());

         if(findFile.isPresent()) {
             var result = findFile.get();
             Assertions.assertEquals(result.getFileName(), "image");
             entityManager.remove(file);
             Optional<File> deleteFile = this.fileRepository.findById(file.getId());
             if (deleteFile.isPresent()) {
                 Assertions.assertNull(deleteFile.get());
             }
         } else {
             Assertions.assertNotNull(findFile.get());
         }
     }

     private File setUpByFile(FileInfo fileInfo, String fileName, String fileUrl, FileStatus status) {
         var file = new File();
         file.setFileInfo(fileInfo);
         file.setFileName(fileName);
         file.setFileUrl(fileUrl);
         file.setStatus(status);
         return this.entityManager.persist(file);
     }

     private FileInfo setUpByFileInfo(FileType fileType) {
         var fileInfo = new FileInfo();
         fileInfo.setType(fileType);
         return this.entityManager.persist(fileInfo);
     }

      File createFileInfo() {
         FileInfo fileInfo = setUpByFileInfo(FileType.PLACE);
         File file = setUpByFile(fileInfo, "image", "jsadjwnqjkdnjskandjskandjka", FileStatus.WAIT);
         return file;
     }
 }
