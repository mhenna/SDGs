package com.techdev.sdg.Utils;

import com.techdev.sdg.DirectionToImpact.DirectionToImpact;
import com.techdev.sdg.PrivateSector.PrivateSector;
import com.techdev.sdg.Project.Project;
import com.techdev.sdg.Resource.Resource;
import com.techdev.sdg.WorkLocation.WorkLocation;
import net.minidev.json.JSONValue;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class Utils {

    public List<Long> getIdsListFromReqBody(Map<String, Object> body, String field) {
        try {
            List<Integer> idIntegers = JSONValue.parse(body.get(field).toString(), ArrayList.class);
            List<Long> idLongs = idIntegers.stream().map(Integer::longValue).collect(Collectors.toList());
            return idLongs;
        } catch (Exception e) {
            return null;
        }
    }

    public List<String> getStrListFromReqBodyObjects(Map<String, Object> body, String field) {
        try {
            List<String> viewersList = JSONValue.parse(body.get(field).toString(), ArrayList.class);
            List<String> viewers = viewersList.stream().map(String::toString).collect(Collectors.toList());
            return viewers;
        } catch (Exception e) {
            return null;
        }
    }
}
