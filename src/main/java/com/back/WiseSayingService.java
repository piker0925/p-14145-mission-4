package com.back;

import java.util.ArrayList;
import java.util.List;

public class WiseSayingService {
    private final WiseSayingRepository wiseSayingRepository = new WiseSayingRepository();

    public WiseSaying write(String content, String author) {
        return wiseSayingRepository.save(content, author);
    }

    public List<WiseSaying> findAll(String keywordType, String keyword, int page, int pageSize) {
        List<WiseSaying> filtered = wiseSayingRepository.findAll().stream()
                .filter(ws -> {
                    if (keyword.isEmpty()) return true;
                    if (keywordType.equals("all")) return ws.getContent().contains(keyword) || ws.getAuthor().contains(keyword);
                    if (keywordType.equals("content")) return ws.getContent().contains(keyword);
                    if (keywordType.equals("author")) return ws.getAuthor().contains(keyword);
                    return false;
                })
                .toList();

        List<WiseSaying> sorted = filtered.reversed();

        List<WiseSaying> result = new ArrayList<>();
        int startIndex = (page - 1) * pageSize;

        for (int i = startIndex; i < startIndex + pageSize && i < sorted.size(); i++) {
            result.add(sorted.get(i));
        }
        
        return result;
    }

    public int getTotalCount(String keywordType, String keyword) {
        return (int) wiseSayingRepository.findAll().stream()
                .filter(ws -> {
                    if (keyword.isEmpty()) return true;
                    if (keywordType.equals("all")) return ws.getContent().contains(keyword) || ws.getAuthor().contains(keyword);
                    if (keywordType.equals("content")) return ws.getContent().contains(keyword);
                    if (keywordType.equals("author")) return ws.getAuthor().contains(keyword);
                    return false;
                }).count();
    }

    public WiseSaying findById(int id) {
        return wiseSayingRepository.findById(id);
    }

    public void remove(WiseSaying ws) {
        wiseSayingRepository.remove(ws);
    }

    public void modify(WiseSaying ws, String content, String author) {
        ws.setContent(content);
        ws.setAuthor(author);
    }
}
