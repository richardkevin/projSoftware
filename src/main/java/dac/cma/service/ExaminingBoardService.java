package dac.cma.service;

import dac.cma.dao.ExaminingBoardDAO;
import dac.cma.model.ExaminingBoard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExaminingBoardService {
    @Autowired
    private ExaminingBoardDAO examiningBoardDAO;

    public void save(ExaminingBoard board) {
        examiningBoardDAO.save(board);
    }
}
