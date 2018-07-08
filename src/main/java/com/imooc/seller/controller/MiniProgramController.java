package com.imooc.seller.controller;

import com.imooc.common.VO.ResultVO;
import com.imooc.common.dataobject.MiniProgramInfo;
import com.imooc.common.enums.MiniStatusEnum;
import com.imooc.common.enums.ResultEnum;
import com.imooc.common.utils.ResultVOUtil;
import com.imooc.exception.SellException;
import com.imooc.seller.service.IMiniProgramInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: JieMin
 * @Description:
 * @Date: created in 15:16 2018/7/8
 */
@RestController
@RequestMapping("/mini")
public class MiniProgramController {

    @Autowired
    private IMiniProgramInfoService miniProgramInfoService;

    private final String MINI_ID = "mini";

    /**
     * 开店or打烊
     *
     * @return
     */
    @GetMapping("/close/or/open")
    public ResultVO closeOrOpen() {
        MiniProgramInfo programInfo = miniProgramInfoService.findOne(MINI_ID);
        if (programInfo == null) {
            throw new SellException(ResultEnum.MINI_NOTEXIST);
        }
        if (programInfo.getStatus() == MiniStatusEnum.OPEN.getCode()) {
            programInfo.setStatus(MiniStatusEnum.CLOSE.getCode());
        } else if (programInfo.getStatus() == MiniStatusEnum.CLOSE.getCode()) {
            programInfo.setStatus(MiniStatusEnum.OPEN.getCode());
        }
        miniProgramInfoService.update(programInfo);
        return ResultVOUtil.success();
    }

    @GetMapping("/isOpen")
    public ResultVO isOpen() {
        MiniProgramInfo programInfo = miniProgramInfoService.findOne(MINI_ID);
        return ResultVOUtil.success(programInfo);
    }

}
