package com.power.assistant.facade;

import com.power.assistant.base.DataModel;
import com.power.assistant.base.FileUtils;
import com.power.assistant.base.PageModel;
import com.power.assistant.base.PageParam;
import com.power.assistant.core.annotation.Authentication;
import com.power.assistant.core.service.MemberService;
import com.power.assistant.core.service.OrgService;
import com.power.assistant.model.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author wuhanhong
 * @date 2018 - 04 - 30
 */
@RestController
@RequestMapping("/member")
public class MemberController {

    @Value("${config.ueditor.serverPath.profile}")
    private String serverPath;
    @Value("${web.profile}")
    private String profilePath;
    @Autowired
    private MemberService memberService;
    @Autowired
    private OrgService orgService;

    @RequestMapping("/info")
    public ModelAndView member(@RequestParam("memberId") Long memberId){
        ModelAndView modelAndView = new ModelAndView("memberDetail");
        //查询详细资料
        MemberVo member = memberService.memberDetail(memberId);

        PageParam pageParam = new PageParam();
        pageParam.setOffset(0);
        pageParam.setLimit(10000);
        PageModel pageModel = memberService.integrationList(pageParam,memberId);
        //支部介绍
        String detail = "";
        OrgVo org = orgService.orgInfo(member.getOrgId());
        detail = org.getDetail();
        String content = (String) memberService.contentQuery().getData();

        modelAndView.getModelMap()
                .addAttribute("member",member)
                .addAttribute("integrations",pageModel.getRes())
                .addAttribute("activities",memberService.activityQuery(member.getOrgId()))
                .addAttribute("content",content)
                .addAttribute("orgDetail",detail);
        return modelAndView;
    }

    @Authentication
    @PostMapping("/party/list")
    public PageModel memberList(PageParam param,Long orgId,String name){
        try {
            return memberService.partyList(param,orgId,name);
        } catch (Exception e) {
            return PageModel.error();
        }
    }

    @Authentication
    @GetMapping("/party/delete")
    public DataModel deleteMember(Long memberId){
        try {
            return DataModel.ok(memberService.deleteMember(memberId));
        } catch (Exception e) {
            return DataModel.error(e.getMessage());
        }
    }

    @Authentication
    @PostMapping("/party/saveOrUpdate")
    public DataModel saveOrUpdateMember(Member member, MultipartFile photo) {
        try {
            if (photo != null) {
                String[] upload = FileUtils.upload(photo,serverPath,profilePath);
                member.setPhotosrc(upload[0]);
            }
            return DataModel.ok(memberService.saveOrUpdate(member));
        } catch (Exception e) {
            e.printStackTrace();
            return DataModel.error(e.getMessage());
        }
    }
    @Authentication
    @PostMapping("/party/saveOrUpdate2Cache")
    public DataModel saveOrUpdate2Cache(Member member, MultipartFile photo) {
        try {
            if (photo != null) {
                String[] upload = FileUtils.upload(photo,serverPath,profilePath);
                member.setPhotosrc(upload[0]);
            }

            return DataModel.ok(memberService.saveOrUpdate(member));
        } catch (Exception e) {
            e.printStackTrace();
            return DataModel.error(e.getMessage());
        }
    }

    @Authentication
    @PostMapping("/activity/list")
    public PageModel activityList(PageParam param,Long orgId){
        try {
            return memberService.activityList(param,orgId);
        } catch (Exception e) {
            return PageModel.error();
        }
    }

    @Authentication
    @PostMapping("/activity/saveOrUpdate")
    public DataModel saveOrUpdateActivity(Activity activity) {
        try {
            return DataModel.ok(memberService.saveOrUpdateActivity(activity));
        } catch (Exception e) {
            e.printStackTrace();
            return DataModel.error(e.getMessage());
        }
    }

    @Authentication
    @GetMapping("/activity/delete")
    public DataModel deleteActivity(Long activityId){
        try {
            return DataModel.ok(memberService.deleteActivity(activityId));
        } catch (Exception e) {
            return DataModel.error(e.getMessage());
        }
    }

    @Authentication
    @PostMapping("/integration/list")
    public PageModel integrationList(PageParam param,Long memberId){
        try {
            return memberService.integrationList(param,memberId);
        } catch (Exception e) {
            return PageModel.error();
        }
    }

    @Authentication
    @PostMapping("/integration/saveOrUpdate")
    public DataModel saveOrUpdateIntegrationMember(IntegrationMember integrationMember) {
        try {
            return DataModel.ok(memberService.saveOrUpdateIntegration(integrationMember));
        } catch (Exception e) {
            e.printStackTrace();
            return DataModel.error(e.getMessage());
        }
    }

    @Authentication
    @PostMapping("/content/saveOrUpdate")
    public DataModel saveOrUpdateContent(String content,Long id) {
        try {
            return DataModel.ok(memberService.saveOrUpdateContent(content,id));
        } catch (Exception e) {
            e.printStackTrace();
            return DataModel.error(e.getMessage());
        }
    }

    @Authentication
    @GetMapping("/content/query")
    public DataModel contentQuery(){
        try {
            return memberService.contentQuery();
        } catch (Exception e) {
            return DataModel.error(e.getMessage());
        }
    }

    @Authentication
    @GetMapping("/integration/delete")
    public DataModel deleteIntegrationMember(Long integrationId){
        try {
            return DataModel.ok(memberService.deleteIntegrationMember(integrationId));
        } catch (Exception e) {
            return DataModel.error(e.getMessage());
        }
    }

    @Authentication
    @RequestMapping("/integration/export")
    public void exportIntegrationMember(String memberIds, HttpServletRequest request, HttpServletResponse response){
        try {
            List<IntegrationMember> members = memberService.selectIntegrationMember(memberIds);

            String name =  System.currentTimeMillis() + ".xls";
            response.addHeader("Content-Disposition",
                    "attachment;filename=" + new String(name.getBytes("UTF-8"), "ISO8859-1"));
            response.setContentType("application/octet-stream");
            OutputStream toClient = response.getOutputStream();
            exportExcel(members, new String[]{"党员姓名","年度","主题内容","分数"}, toClient);
            toClient.flush();
            toClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void exportExcel(List<IntegrationMember> list, String[] column,OutputStream os) {

        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("1");
        sheet.setDefaultColumnWidth((short) 15);
        HSSFCellStyle style = wb.createCellStyle();
        HSSFRow row = sheet.createRow(0);
        style.setAlignment(HorizontalAlignment.CENTER);

        for (int index = 0; index < column.length; index++) {
            HSSFCell cell = row.createCell((short) index);
            // 名称..
            cell.setCellValue(column[index]);
            cell.setCellStyle(style);
        }
        int rowNum = 1;
        for (IntegrationMember integrationMember : list) {
            row = sheet.createRow(rowNum);
            HSSFCell cell = row.createCell(0);
            cell.setCellValue(integrationMember.getMemberName());
            cell = row.createCell(1);
            cell.setCellValue(integrationMember.getYearNo());
            cell = row.createCell(2);
            cell.setCellValue(integrationMember.getContent());
            cell = row.createCell(3);
            cell.setCellValue(integrationMember.getScore());
            rowNum++;
        }
        try {
            wb.write(os);
            System.out.println("导出成功");
            wb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
