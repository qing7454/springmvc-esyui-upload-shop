package business.task.service;

import com.sys.service.ICommonService;
import org.springframework.web.multipart.MultipartFile;

public interface TaskService  extends ICommonService {

    /**
     * 导入任务
     * @param taskFile
     * @return
     */
    boolean importTask(MultipartFile taskFile);
}
