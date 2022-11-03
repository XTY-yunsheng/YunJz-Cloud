import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function listType(query) {
  return request({
    url: '/account/type/list',
    method: 'get',
    params: query
  })
}

// 查询等级名称列表
export function getTypeNameList() {
  return request({
    url: '/account/type/list/name',
    method: 'get'
  })
}

// 查询【请填写功能名称】详细
export function getType(typeId) {
  return request({
    url: '/account/type/' + typeId,
    method: 'get'
  })
}

// 新增【请填写功能名称】
export function addType(data) {
  return request({
    url: '/account/type',
    method: 'post',
    data: data
  })
}

// 修改【请填写功能名称】
export function updateType(data) {
  return request({
    url: '/account/type',
    method: 'put',
    data: data
  })
}

//修改用户状态
export function updateCategoryStatus(data) {
  return request({
    url: '/account/type/status',
    method: 'post',
    data: data
  })
}

// 删除【请填写功能名称】
export function delType(typeId) {
  return request({
    url: '/account/type/' + typeId,
    method: 'delete'
  })
}
