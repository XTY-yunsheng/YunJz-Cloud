import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function listRelation(query) {
  return request({
    url: '/account/ub_relation/list',
    method: 'get',
    params: query
  })
}

// 查询可用用户名称列表
export function getUserNameList() {
  return request({
    url: '/account/ub_relation/list/username',
    method: 'get'
  })
}

// 查询【请填写功能名称】详细
export function getRelation(id) {
  return request({
    url: '/account/ub_relation/' + id,
    method: 'get'
  })
}

// 新增【请填写功能名称】
export function addRelation(data) {
  return request({
    url: '/account/ub_relation',
    method: 'post',
    data: data
  })
}

// 修改【请填写功能名称】
export function updateRelation(data) {
  return request({
    url: '/account/ub_relation',
    method: 'put',
    data: data
  })
}

// 删除【请填写功能名称】
export function delRelation(id) {
  return request({
    url: '/account/ub_relation/' + id,
    method: 'delete'
  })
}
