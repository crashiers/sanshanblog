package xyz.sanshan.search.pojo.VO;

import lombok.Data;

import java.util.List;

@Data
public class ElasticResponseVO {

    private List<ElasticSearchResultDTO> result;

    private  Long total;

}
