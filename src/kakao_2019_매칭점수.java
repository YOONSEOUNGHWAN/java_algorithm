import java.util.*;

public class kakao_2019_매칭점수 {

    class Solution {
        class Page{
            int idx;
            int basic, link;
            double score;
            Page(int idx, int basic, int link, double score){
                this.idx = idx;
                this.basic = basic;
                this.link = link;
                this.score = score;
            }
        }

        class Comp implements Comparator<Page>{
            public int compare(Page a, Page b){
                if(a.score == b.score)
                    return a.idx - b.idx;
                else if(a.score < b.score){
                    return 1;
                }else
                    return -1;
            }
        }
        public int solution(String word, String[] pages) {
            int wsize = word.length();
            Map<String, Integer> pageMap = new HashMap<String, Integer>();
            List<Page> pageList = new ArrayList<Page>();
            word = word.toLowerCase();
            for(int i=0; i<pages.length; i++){
                String s = pages[i] = pages[i].toLowerCase();
                int mid = 0, posL = 0, posR = 0;
                while(mid <= posL){
                    posL = s.indexOf("<meta", posL + 1);
                    posR = s.indexOf(">", posL);
                    mid = s.lastIndexOf("https://", posR);
                }
                posR = s.indexOf("\"", mid);
                String url = s.substring(mid, posR);

                posL = s.indexOf("<body>", posR);
                int basic = 0;
                for(int start = posL;;){
                    start = s.indexOf(word, start + 1);
                    if(start == -1) break;
                    if(!Character.isLetter(s.charAt(start-1)) && !Character.isLetter(s.charAt(start+wsize))){
                        basic++;
                        start += wsize;
                    }
                }
                int link = 0;
                for(int start = posL;;){
                    start = s.indexOf("<a href", start+1);
                    if(start == -1) break;
                    link++;
                }
                pageMap.put(url, i);
                pageList.add(new Page(i, basic, link, (double)basic));
            }

            for(int i=0; i<pages.length; i++){
                String s = pages[i];
                for(int posL = 0, posR = 0; ;){
                    posL = s.indexOf("<a href", posR);
                    if(posL == -1) break;

                    posL = s.indexOf("https://", posL);
                    posR = s.indexOf("\"", posL);
                    String linkUrl = s.substring(posL, posR);

                    Integer value = pageMap.get(linkUrl);
                    if(value != null){
                        pageList.get(value).score += (double)pageList.get(i).basic / pageList.get(i).link;
                    }
                }
            }
            pageList.sort(new Comp());
            return pageList.get(0).idx;

        }
    }

}
