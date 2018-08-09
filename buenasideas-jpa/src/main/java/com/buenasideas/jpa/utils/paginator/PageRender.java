package com.buenasideas.jpa.utils.paginator;

import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class PageRender<T> {

    private String url;
    private Page<T> page;
    private int totalPaginas;
    private int numElementporPagina;
    private int paginaActual;
    private List<PageItem> paginas;

    public PageRender(String url, Page<T> page) {
        this.url = url;
        this.page = page;
        totalPaginas = page.getSize();
        numElementporPagina = page.getTotalPages();
        paginaActual = page.getNumber() + 1;
        this.paginas = new ArrayList<PageItem>();

        int desde, hasta;
        if(totalPaginas <= numElementporPagina){
            desde = 1;
            hasta = totalPaginas;
        } else {
            if(paginaActual <= numElementporPagina/2){
                desde = 1;
                hasta = numElementporPagina;
            } else if(paginaActual >= totalPaginas - numElementporPagina/2){
                desde = totalPaginas - numElementporPagina + 1;
                hasta = numElementporPagina;
            } else {
                desde = paginaActual - numElementporPagina/2;
                hasta = numElementporPagina;
            }
        }

        for (int i = 0; i < hasta; i++){
            paginas.add(new PageItem(desde + i, paginaActual == desde + i));
        }

    }

    public boolean isFirst(){
        return page.isFirst();
    }

    public boolean isLast(){
        return page.isLast();
    }

    public boolean isHasNext(){
        return page.hasNext();
    }

    public boolean isHasPrevious(){
        return page.hasPrevious();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Page<T> getPage() {
        return page;
    }

    public void setPage(Page<T> page) {
        this.page = page;
    }

    public int getTotalPaginas() {
        return totalPaginas;
    }

    public void setTotalPaginas(int totalPaginas) {
        this.totalPaginas = totalPaginas;
    }

    public int getNumElementporPagina() {
        return numElementporPagina;
    }

    public void setNumElementporPagina(int numElementporPagina) {
        this.numElementporPagina = numElementporPagina;
    }

    public int getPaginaActual() {
        return paginaActual;
    }

    public void setPaginaActual(int paginaActual) {
        this.paginaActual = paginaActual;
    }

    public List<PageItem> getPaginas() {
        return paginas;
    }

    public void setPaginas(List<PageItem> paginas) {
        this.paginas = paginas;
    }
}
