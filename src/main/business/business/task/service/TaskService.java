package business.task.service;

import com.sys.service.ICommonService;
import org.springframework.web.multipart.MultipartFile;

public interface TaskService  extends ICommonService {

    /**
     * ��������
     * @param taskFile
     * @return
     */
    String importTask(MultipartFile taskFile);
}
