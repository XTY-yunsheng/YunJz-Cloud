import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function listLevel(query) {
  return request({
    url: '/member/level/list',
    method: 'get',
    params: query
  })
}

// 查询等级名称列表
export function getLevelList() {
  return request({
    url: '/member/level/list/name',
    method: 'get'
  })
}

// 查询【请填写功能名称】详细
export function getLevel(levelId) {
  return request({
    url: '/member/level/' + levelId,
    method: 'get'
  })
}

// 新增【请填写功能名称】
export function addLevel(data) {
  return request({
    url: '/member/level',
    method: 'post',
    data: data
  })
}

// 修改【请填写功能名称】
export function updateLevel(data) {
  return request({
    url: '/member/level',
    method: 'put',
    data: data
  })
}

//修改等级状态
export function updateLevelStatus(data) {
  return request({
    url: '/member/level/status',
    method: 'post',
    data: data
  })
}

// 删除【请填写功能名称】
export function delLevel(levelId) {
  return request({
    url: '/member/level/' + levelId,
    method: 'delete'
  })
}
