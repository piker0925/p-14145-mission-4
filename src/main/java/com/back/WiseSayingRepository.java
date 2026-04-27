package com.back;

import java.util.ArrayList;
import java.util.List;

public class WiseSayingRepository {
    private int count = 0;
    private final List<WiseSaying> wiseSayings = new ArrayList<>();

    public WiseSayingRepository() {
        if (wiseSayings.isEmpty()) {
            for (int i = 1; i <= 10; i++) {
                save("명언 " + i, "작자미상 " + i);
            }
        }
    }

    public WiseSaying save(String content, String author) {
        count++;
        WiseSaying ws = new WiseSaying(count, content, author);
        wiseSayings.add(ws);
        return ws;
    }

    public List<WiseSaying> findAll() {
        return wiseSayings;
    }

    public WiseSaying findById(int id) {
        for (WiseSaying ws : wiseSayings) {
            if (ws.getId() == id) return ws;
        }
        return null;
    }

    public void remove(WiseSaying ws) {
        wiseSayings.remove(ws);
    }
}
