package dac.cma.dao;

import dac.cma.model.ExaminingBoard;
import dac.cma.repository.ExaminingBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ExaminingBoardDAO {
    @Autowired
    private ExaminingBoardRepository examiningBoardRepository;

    public void save(ExaminingBoard board) {
        examiningBoardRepository.save(board);
    }
}
