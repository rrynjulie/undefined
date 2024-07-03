package com.lec.spring.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Controller
public class TicketController {
    @Value("${app.api-key.bus}")
    private String busApiKey;

    private static final Logger logger = Logger.getLogger(UserController.class.getName());

    @RequestMapping("/ticketcheck")
    public String ticketcheck() {
        return "ticket/TicketCheck";
    }

    @RequestMapping("/fetchBusInfo")
    public String getBusInfo(
            @RequestParam String depTerminalId,
            @RequestParam String arrTerminalId,
            @RequestParam String depPlandTime,
            @RequestParam String busGradeId,
            @RequestParam(required = false) String returnPlandTime,
            Model model
    ) {
        RestTemplate rt = new RestTemplate();
        String baseUrl = "https://apis.data.go.kr/1613000/ExpBusInfoService/getStrtpntAlocFndExpbusInfo";

        // 편도 정보 요청
        List<Map<String, String>> busInfoList = fetchBusInfo(rt, baseUrl, depTerminalId, arrTerminalId, depPlandTime, busGradeId);
        logger.info("편도 정보 리스트: " + busInfoList.toString());

        // 왕복 정보 요청
        List<Map<String, String>> returnBusInfoList = null;
        if (returnPlandTime != null && !returnPlandTime.isEmpty()) {
            returnBusInfoList = fetchBusInfo(rt, baseUrl, arrTerminalId, depTerminalId, returnPlandTime, busGradeId);
            logger.info("왕복 정보 리스트: " + returnBusInfoList.toString());
        }

        model.addAttribute("busInfoList", busInfoList);
        model.addAttribute("returnBusInfoList", returnBusInfoList);
        return "ticket/TicketList";
    }

    private List<Map<String, String>> fetchBusInfo(RestTemplate rt, String baseUrl, String depTerminalId, String arrTerminalId, String depPlandTime, String busGradeId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("serviceKey", busApiKey)
                .queryParam("depTerminalId", depTerminalId)
                .queryParam("arrTerminalId", arrTerminalId)
                .queryParam("depPlandTime", depPlandTime)
                .queryParam("busGradeId", busGradeId)
                .queryParam("_type", "json");

        URI uri = builder.build().toUri();

        logger.info("Request URL: " + uri.toString());

        ResponseEntity<String> response = rt.exchange(uri, HttpMethod.GET, new HttpEntity<>(headers), String.class);

        logger.info("API Response: " + response.getBody());

        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, String>> busInfoList = new ArrayList<>();
        try {
            JsonNode root = objectMapper.readTree(response.getBody());
            JsonNode items = root.path("response").path("body").path("items").path("item");
            if (items.isArray()) {
                for (JsonNode item : items) {
                    Map<String, String> busInfo = objectMapper.convertValue(item, Map.class);
                    busInfoList.add(busInfo);
                }
            }
        } catch (Exception e) {
            logger.severe("Error parsing JSON response: " + e.getMessage());
        }

        return busInfoList;
    }
}
